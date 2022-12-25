package com.adammcneilly.pocketleague.feature.feed

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.data.match.MatchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

/**
 * Manages the state of the feed screen.
 *
 * @property[eventRepository] Data source for requesting event information
 * on the feed screen.
 * @property[matchRepository] Data source for requesting match information
 * on the feed screen.
 */
class FeedPresenter(
    private val eventRepository: EventRepository,
    private val matchRepository: MatchRepository,
) {

    private val _state = MutableStateFlow(FeedViewState())
    val state = _state.asStateFlow()

    fun init(scope: CoroutineScope) {
        observePastWeeksMatches(scope)
        observeOngoingEvents(scope)
        observeUpcomingEvents(scope)
    }

    private fun observePastWeeksMatches(scope: CoroutineScope) {
        matchRepository
            .getPastWeeksMatches()
            .onEach { matchList ->
                val displayModels = matchList.map(Match::toDetailDisplayModel)

                _state.update {
                    it.copy(
                        recentMatches = displayModels,
                    )
                }
            }
            .launchIn(scope)
    }

    private fun observeOngoingEvents(scope: CoroutineScope) {
        eventRepository
            .getOngoingEvents()
            .onEach { eventList ->
                val displayModels = eventList.map(Event::toSummaryDisplayModel)

                _state.update {
                    it.copy(
                        ongoingEvents = displayModels,
                    )
                }
            }
            .launchIn(scope)
    }

    private fun observeUpcomingEvents(scope: CoroutineScope) {
        eventRepository
            .getUpcomingEvents()
            .onEach { eventList ->
                val displayModels = eventList.map(Event::toSummaryDisplayModel)

                _state.update {
                    it.copy(
                        upcomingEvents = displayModels,
                    )
                }
            }
            .launchIn(scope)
    }
}
