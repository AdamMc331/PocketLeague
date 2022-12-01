package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.event.EventListRequest
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

const val NUM_DAYS_RECENT_MATCHES = 7

/**
 * Loads the information for the feed state.
 */
fun Events.loadFeed() = screenCoroutine { scope ->
    appModule
        .dataModule
        .eventService
        .sync()

    fetchRecentMatches(scope)

    scope.launch {
        fetchOngoingEvents()
    }

    fetchUpcomingEvents(scope)
}

private fun Events.fetchRecentMatches(
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .matchService
        .getPastWeeksMatches()
        .onEach { matchList ->
            val displayModels = matchList.map(Match::toDetailDisplayModel)

            stateManager.updateScreen(FeedViewState::class) {
                it.copy(
                    recentMatchesState = DataState.Success(displayModels),
                )
            }
        }
        .launchIn(scope)
}

private suspend fun Events.fetchOngoingEvents() {
    val ongoingEventsRequest = EventListRequest(
        date = Clock.System.now(),
//        group = "rlcs",
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
