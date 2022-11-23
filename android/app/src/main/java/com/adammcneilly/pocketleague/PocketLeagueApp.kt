package com.adammcneilly.pocketleague

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.adammcneilly.pocketleague.shared.di.AppModule
import com.adammcneilly.pocketleague.shared.di.ProdAppModule
import com.adammcneilly.pocketleague.shared.screens.DKMPViewModel
import com.adammcneilly.pocketleague.shared.screens.getAndroidInstance

/**
 * This is our custom implementation of the [Application] class. This will do any dependency injection
 * or third party initializations that must occur at app startup.
 */
open class PocketLeagueApp : Application() {

    lateinit var viewModel: DKMPViewModel

    open val appModule: AppModule = ProdAppModule()

    override fun onCreate() {
        super.onCreate()
        viewModel = DKMPViewModel.Factory.getAndroidInstance(
            appModule = this.appModule,
        )

        val appLifecycleObserver = AppLifecycleObserver(viewModel)
        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)
    }
}

/**
 * Observes lifecycle events of the Android app and calls appropriate methods on the [viewModel]
 * to manage screen states.
 */
class AppLifecycleObserver(private val viewModel: DKMPViewModel) : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                // Avoid calling at app startup
                if (viewModel.stateFlow.value.recompositionIndex > 0) {
                    viewModel.navigation.onReEnterForeground()
                }
            }
            Lifecycle.Event.ON_STOP -> {
                viewModel.navigation.onEnterBackground()
            }
            else -> {
                // Don't care
            }
        }
    }
}
