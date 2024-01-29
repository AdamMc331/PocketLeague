package com.adammcneilly.pocketleague.feature.debugmenu

import com.adammcneilly.pocketleague.core.feature.CommonParcelize
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

/**
 * UI screen for debug menu options such as setting the date to use in the application.
 */
@CommonParcelize
object DebugMenuScreen : Screen {
    /**
     * UI state for the [DebugMenuScreen].
     */
    object State : CircuitUiState

    /**
     * UI events triggered by user interactions on the [DebugMenuScreen].
     */
    sealed interface Event : CircuitUiEvent

    /**
     * Factory to create [DebugMenuContent] based on a [State].
     */
    object UiFactory : Ui.Factory {
        override fun create(
            screen: Screen,
            context: CircuitContext,
        ): Ui<*>? {
            return when (screen) {
                DebugMenuScreen -> {
                    ui<State> { state, modifier ->
                        DebugMenuContent(modifier)
                    }
                }
                else -> null
            }
        }
    }

    /**
     * Factory to create a [DebugMenuPresenter].
     */
    object PresenterFactory : Presenter.Factory {
        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext,
        ): Presenter<*>? {
            return when (screen) {
                DebugMenuScreen -> {
                    DebugMenuPresenter()
                }
                else -> null
            }
        }
    }
}
