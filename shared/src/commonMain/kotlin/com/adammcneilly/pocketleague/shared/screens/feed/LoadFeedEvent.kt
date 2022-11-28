package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.MatchListRequest
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    fetchUpcomingEvents(scope)
    fetchOngoingEvents(scope)
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

            stateManager.updateScreen(FeedViewState::class) {
                it.copy(
                    recentMatchesState = mappedResult,
                )
            }
        }
}

private fun Events.fetchOngoingEvents(
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .eventService
        .getOngoingEvents()
        .onEach { eventList ->
            stateManager.updateScreen(FeedViewState::class) {
                val displayModelList = eventList.map(Event::toSummaryDisplayModel)

                it.copy(
                    ongoingEventsState = DataState.Success(displayModelList),
                )
            }
        }
        .launchIn(scope)
}

private fun Events.fetchUpcomingEvents(
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .eventService
        .getUpcomingEvents()
        .onEach { eventList ->
            stateManager.updateScreen(FeedViewState::class) {
                val displayModelList = eventList.map(Event::toSummaryDisplayModel)

                it.copy(
                    upcomingEventsState = DataState.Success(displayModelList),
                )
            }
        }
        .launchIn(scope)
}
