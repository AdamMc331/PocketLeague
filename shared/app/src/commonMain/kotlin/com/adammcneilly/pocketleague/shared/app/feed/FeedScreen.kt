package com.adammcneilly.pocketleague.shared.app.feed

import com.adammcneilly.pocketleague.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.app.CommonParcelize
import com.adammcneilly.pocketleague.shared.ui.feed.FeedContent
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

/**
 * Initial screen of the app that shows a feed of recent matches,
 * upcoming events, etc.
 */
@CommonParcelize
object FeedScreen : Screen {
    /**
     * UI state for the [FeedScreen].
     */
    data class State(
        val recentMatches: List<MatchDetailDisplayModel>,
        val upcomingEvents: List<EventGroupDisplayModel>,
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    /**
     * Any events that can be triggered by the [FeedScreen].
     */
    sealed interface Event : CircuitUiEvent {

        /**
         * User tapped on an event item.
         */
        data class EventClicked(
            val eventId: String,
        ) : Event

        /**
         * User tapped on a match item.
         */
        data class MatchClicked(
            val matchId: String,
        ) : Event
    }

    object UiFactory : Ui.Factory {
        override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
            return when (screen) {
                FeedScreen -> {
                    ui<State> { state, modifier ->
                        FeedContent(
                            matches = state.recentMatches,
                            eventGroups = state.upcomingEvents,
                            modifier = modifier,
                        )
                    }
                }
                else -> null
            }
        }
    }

    object PresenterFactory : Presenter.Factory {
        override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
            return when (screen) {
                FeedScreen -> FeedPresenter(navigator)
                else -> null
            }
        }
    }
}
