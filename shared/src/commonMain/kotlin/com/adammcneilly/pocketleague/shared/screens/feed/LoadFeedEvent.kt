package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.data.models.EventListRequest
import com.adammcneilly.pocketleague.shared.data.models.MatchListRequest
import com.adammcneilly.pocketleague.shared.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

const val NUM_DAYS_RECENT_MATCHES = 3

/**
 * Loads the information for the feed state.
 */
fun Events.loadFeed() = screenCoroutine {
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val upcomingEventsRequest = EventListRequest(
        after = today,
    )

    repository.eventRepository.fetchEvents(
        upcomingEventsRequest,
    ).collect { repoResult ->
        stateManager.updateScreen(FeedViewState::class) {
            val mappedResult = when (repoResult) {
                is DataState.Loading -> {
                    DataState.Loading
                }
                is DataState.Success -> {
                    DataState.Success(
                        data = repoResult.data.map(Event::toSummaryDisplayModel)
                    )
                }
                is DataState.Error -> {
                    DataState.Error(repoResult.error)
                }
            }

            it.copy(
                upcomingEventsState = mappedResult,
            )
        }
    }

    val recentMatchesRequest = MatchListRequest(
        before = today,
        after = today.date.minus(NUM_DAYS_RECENT_MATCHES, DateTimeUnit.DAY)
            .atStartOfDayIn(TimeZone.currentSystemDefault())
            .toLocalDateTime(TimeZone.currentSystemDefault()),
    )

    repository.matchRepository.fetchMatches(
        request = recentMatchesRequest,
    ).collect { repoResult ->
        stateManager.updateScreen(FeedViewState::class) {
            it.copy(
                recentMatchesState = repoResult,
            )
        }
    }
}
