package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.EventListRequest
import com.adammcneilly.pocketleague.core.data.models.MatchListRequest
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
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
    fetchOngoingEvents()

    fetchRecentMatches()
}

private suspend fun Events.fetchRecentMatches() {
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val recentMatchesRequest = MatchListRequest(
        before = today,
        after = today.date.minus(NUM_DAYS_RECENT_MATCHES, DateTimeUnit.DAY)
            .atStartOfDayIn(TimeZone.currentSystemDefault())
            .toLocalDateTime(TimeZone.currentSystemDefault()),
        group = "rlcs",
    )

    repository.matchRepository.fetchMatches(
        request = recentMatchesRequest,
    ).collect { repoResult ->
        stateManager.updateScreen(FeedViewState::class) {
            val mappedResult = when (repoResult) {
                is DataState.Loading -> {
                    DataState.Loading
                }
                is DataState.Success -> {
                    DataState.Success(
                        data = repoResult.data.map(Match::toDetailDisplayModel)
                    )
                }
                is DataState.Error -> {
                    DataState.Error(repoResult.error)
                }
            }

            it.copy(
                recentMatchesState = mappedResult,
            )
        }
    }
}

private suspend fun Events.fetchOngoingEvents() {
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val ongoingEventsRequest = EventListRequest(
        date = today,
        group = "rlcs",
    )

    repository.eventRepository.fetchEvents(
        ongoingEventsRequest,
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
                ongoingEventsState = mappedResult,
            )
        }
    }
}
