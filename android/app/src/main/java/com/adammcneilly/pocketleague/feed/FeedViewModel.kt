package com.adammcneilly.pocketleague.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.adammcneilly.pocketleague.feature.feed.FeedViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

/**
 * State management container for the feed screen.
 */
class FeedViewModel(
    private val eventRepository: EventRepository,
    private val matchRepository: MatchRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(FeedViewState())
    val state = _state.asStateFlow()

    init {
        observePastWeeksMatches()
        observeOngoingEvents()
        observeUpcomingEvents()
    }

    private fun observeUpcomingEvents() {
        eventRepository
            .getUpcomingEvents()
            .map { eventList ->
                eventList.map(Event::toSummaryDisplayModel)
            }
            .onEach { eventList ->
                _state.update { currentState ->
                    currentState.copy(
                        upcomingEvents = eventList,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeOngoingEvents() {
        eventRepository
            .getOngoingEvents()
            .map { eventList ->
                eventList.map(Event::toSummaryDisplayModel)
            }
            .onEach { eventList ->
                _state.update { currentState ->
                    currentState.copy(
                        ongoingEvents = eventList,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observePastWeeksMatches() {
        matchRepository
            .getMatchesInDateRange()
            .map { matchList ->
                matchList.map(Match::toDetailDisplayModel)
            }
            .onEach { matchList ->
                _state.update { currentState ->
                    currentState.copy(
                        recentMatches = matchList,
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}
