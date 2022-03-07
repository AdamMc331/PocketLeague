package com.adammcneilly.pocketleague

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.adammcneilly.pocketleague.shared.DKMPViewModel
import com.adammcneilly.pocketleague.shared.getAndroidInstance
import dagger.hilt.android.HiltAndroidApp

/**
 * This is our custom implementation of the [Application] class. This will do any dependency injection
 * or third party initializations that must occur at app startup.
 */
@HiltAndroidApp
class PocketLeagueApp : Application() {
    lateinit var viewModel: DKMPViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = DKMPViewModel.Factory.getAndroidInstance()

        val appLifecycleObserver = AppLifecycleObserver(viewModel)
        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)
    }
}

/**
 * A [LifecycleEventObserver] which notifies our [viewModel] of certain lifecycle events.
 */
private class AppLifecycleObserver(private val viewModel: DKMPViewModel) : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                // This check avoids calling at app startup foreground at startup.
                if (viewModel.stateFlow.value.recompositionIndex > 0) {
                    viewModel.navigation.onReEnterForeground()
                }
            }
            Lifecycle.Event.ON_STOP -> {
                viewModel.navigation.onEnterBackground()
            }
        }
    }
}
