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

@CommonParcelize
object DebugMenuScreen : Screen {
    object State : CircuitUiState

    sealed interface Event : CircuitUiEvent

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
