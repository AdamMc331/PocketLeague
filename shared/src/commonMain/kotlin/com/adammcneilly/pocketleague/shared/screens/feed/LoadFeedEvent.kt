package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

const val NUM_DAYS_RECENT_MATCHES = 7

/**
 * Loads the information for the feed state.
 */
fun Events.loadFeed() = screenCoroutine { scope ->
    fetchRecentMatches(scope)
    fetchOngoingEvents(scope)
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

private fun Events.fetchOngoingEvents(
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .eventRepository
        .getOngoingEvents()
        .onEach { eventList ->
            println("ARM - Ongoing Events: $eventList")

            stateManager.updateScreen(FeedViewState::class) {
                val displayModels = eventList.map(Event::toSummaryDisplayModel)

                it.copy(
                    ongoingEventsState = DataState.Success(displayModels)
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
        .eventRepository
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
