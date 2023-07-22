package com.adammcneilly.pocketleague.shared.app.feed

import com.adammcneilly.pocketleague.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.data.match.MatchRepository
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
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

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
        val ongoingEvents: List<EventGroupDisplayModel>,
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

    /**
     * Factory to create the compose UI for the [FeedScreen].
     */
    object UiFactory : Ui.Factory {
        override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
            return when (screen) {
                FeedScreen -> {
                    ui<State> { state, modifier ->
                        FeedContent(
                            recentMatches = state.recentMatches,
                            ongoingEvents = state.ongoingEvents,
                            upcomingEvents = state.upcomingEvents,
                            onMatchClicked = { matchId ->
                                val event = Event.MatchClicked(matchId)
                                state.eventSink.invoke(event)
                            },
                            modifier = modifier,
                        )
                    }
                }

                else -> null
            }
        }
    }

    /**
     * Factory to create a [FeedPresenter] for the [FeedScreen].
     */
    object PresenterFactory : Presenter.Factory, KoinComponent {
        private val matchRepository: MatchRepository by inject()
        private val clock: Clock by inject()

        override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
            return when (screen) {
                FeedScreen -> FeedPresenter(
                    matchRepository = matchRepository,
                    clock = clock,
                    navigator = navigator,
                )

                else -> null
            }
        }
    }
}
