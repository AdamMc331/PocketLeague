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
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * UI screen for debug menu options such as setting the date to use in the application.
 */
@CommonParcelize
object DebugMenuScreen : Screen {
    /**
     * UI state for the [DebugMenuScreen].
     */
    data class State(
        val useSystemTimeProvider: Boolean,
        val debugTimeProviderDate: String,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    /**
     * UI events triggered by user interactions on the [DebugMenuScreen].
     */
    sealed interface Event : CircuitUiEvent {
        /**
         * If true, use the system time provider throughout the app.
         * If false, use a debug time provider.
         */
        data class UseSystemTimeProviderChanged(
            val useSystemTimeProvider: Boolean,
        ) : Event

        /**
         * Stores the [date] that should be supplied to a debug time provider
         * instance.
         */
        data class DebugTimeProviderDateChanged(
            val date: String,
        ) : Event
    }

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
                        DebugMenuContent(state, modifier)
                    }
                }

                else -> null
            }
        }
    }

    /**
     * Factory to create a [DebugMenuPresenter].
     */
    object PresenterFactory : Presenter.Factory, KoinComponent {
        private val debugPreferences: DebugPreferences by inject()

        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext,
        ): Presenter<*>? {
            return when (screen) {
                DebugMenuScreen -> {
                    DebugMenuPresenter(debugPreferences = debugPreferences)
                }

                else -> null
            }
        }
    }
}
