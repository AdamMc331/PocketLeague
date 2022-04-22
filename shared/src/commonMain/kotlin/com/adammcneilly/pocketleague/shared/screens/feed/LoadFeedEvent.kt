package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.models.Event
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect

const val NUM_DAYS_RECENT_MATCHES = 3

/**
 * Loads the information for the feed state.
 */
fun Events.loadFeed() = screenCoroutine {
    val upcomingEventsFlow = dependencies.getUpcomingEventsUseCase.invoke()
    val recentMatchesFlow = dependencies.getRecentMatchesUseCase.invoke(NUM_DAYS_RECENT_MATCHES)

    upcomingEventsFlow.collect { useCaseResult ->
        stateManager.updateScreen(FeedViewState::class) {
            val mappedResult = when (useCaseResult) {
                is DataState.Loading -> {
                    DataState.Loading
                }
                is DataState.Success -> {
                    DataState.Success(
                        data = useCaseResult.data.map(Event::toSummaryDisplayModel)
                    )
                }
                is DataState.Error -> {
                    DataState.Error(useCaseResult.error)
                }
            }

            it.copy(
                upcomingEventsState = mappedResult,
            )
        }
    }

    recentMatchesFlow.collect { useCaseResult ->
        stateManager.updateScreen(FeedViewState::class) {
            it.copy(
                recentMatchesState = useCaseResult,
            )
        }
    }
}
