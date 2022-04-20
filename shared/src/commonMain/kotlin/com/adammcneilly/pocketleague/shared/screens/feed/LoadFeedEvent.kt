package com.adammcneilly.pocketleague.shared.screens.feed

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
            it.copy(
                upcomingEventsState = useCaseResult,
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
