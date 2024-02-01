package com.adammcneilly.pocketleague.feature.debugmenu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.runtime.presenter.Presenter

/**
 * State management for the [DebugMenuScreen].
 */
internal class DebugMenuPresenter(
    private val debugPreferences: DebugPreferences,
) : Presenter<DebugMenuScreen.State> {
    @Composable
    override fun present(): DebugMenuScreen.State {
        var useSystemTimeProvider by remember {
            mutableStateOf(debugPreferences.useSystemTimeProvider)
        }

        var debugTimeProviderDate by remember {
            mutableStateOf(debugPreferences.debugTimeProviderDate)
        }

        return DebugMenuScreen.State(
            useSystemTimeProvider = useSystemTimeProvider,
            debugTimeProviderDate = debugTimeProviderDate,
        ) { event ->
            // I don't like using two different lines here, one for local state and one
            // for preference store. Let's look and see if multiplatform settings
            // has some observable logic we can use.
            when (event) {
                is DebugMenuScreen.Event.DebugTimeProviderDateChanged -> {
                    debugTimeProviderDate = event.date
                    debugPreferences.debugTimeProviderDate = event.date
                }
                is DebugMenuScreen.Event.UseSystemTimeProviderChanged -> {
                    useSystemTimeProvider = event.useSystemTimeProvider
                    debugPreferences.useSystemTimeProvider = event.useSystemTimeProvider
                }
            }
        }
    }
}
