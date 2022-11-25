package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.DataDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.event.EventListRequest
import com.adammcneilly.pocketleague.data.match.MatchListRequest
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

const val NUM_DAYS_RECENT_MATCHES = 7

/**
 * Loads the information for the feed state.
 */
fun Events.loadFeed() = screenCoroutine { scope ->
    scope.launch {
        fetchRecentMatches()
    }

    scope.launch {
        fetchOngoingEvents()
    }

    scope.launch {
        fetchUpcomingEvents()
    }
}

private suspend fun Events.fetchRecentMatches() {
    val recentMatchesRequest = MatchListRequest(
        before = Clock.System.now(),
        after = Clock.System.now().minus(NUM_DAYS_RECENT_MATCHES.days),
        group = "rlcs",
    )

    appModule
        .dataModule
        .matchService
        .fetchMatches(recentMatchesRequest)
        .collect { matchListResult ->
            val mappedResult = matchListResult.map { matches ->
                matches.map(Match::toDetailDisplayModel)
            }

            val otherMappedResult: DataDisplayModel<List<MatchDetailDisplayModel>> = when (mappedResult) {
                is DataState.Error -> {
                    // We should provide a default here.
                    DataDisplayModel.Error(mappedResult.error.message.orEmpty())
                }
                DataState.Loading -> {
                    DataDisplayModel.Loading
                }
                is DataState.Success -> {
                    DataDisplayModel.Content(mappedResult.data)
                }
            }

            stateManager.updateScreen(FeedViewState::class) {
                it.copy(
                    recentMatchesState = otherMappedResult,
                )
            }
        }
}

private suspend fun Events.fetchOngoingEvents() {
    val ongoingEventsRequest = EventListRequest(
        date = Clock.System.now(),
        group = "rlcs",
    )

    val repoResult = appModule
        .dataModule
        .eventService
        .fetchEvents(ongoingEventsRequest)

    stateManager.updateScreen(FeedViewState::class) {
        val mappedResult = when (repoResult) {
            is DataState.Loading -> {
                DataState.Loading
            }
            is DataState.Success -> {
                DataState.Success(
                    data = repoResult.data
                        .sortedBy(Event::startDateUTC)
                        .map(Event::toSummaryDisplayModel)
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

private suspend fun Events.fetchUpcomingEvents() {
    val upcomingEventsRequest = EventListRequest(
        after = Clock.System.now(),
        group = "rlcs",
    )

    val repoResult = appModule
        .dataModule
        .eventService
        .fetchEvents(upcomingEventsRequest)

    stateManager.updateScreen(FeedViewState::class) {
        val mappedResult = when (repoResult) {
            is DataState.Loading -> {
                DataState.Loading
            }
            is DataState.Success -> {
                DataState.Success(
                    data = repoResult.data
                        .sortedBy(Event::startDateUTC)
                        .map(Event::toSummaryDisplayModel)
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
