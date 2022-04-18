package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.shared.eventlist.GetUpcomingEventsUseCase
import com.adammcneilly.pocketleague.shared.matchlist.GetRecentMatchesUseCase
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
            when (useCaseResult) {
                is GetUpcomingEventsUseCase.Result.Success -> {
                    it.copy(
                        showLoading = false,
                        upcomingEvents = useCaseResult.events,
                    )
                }
                is GetUpcomingEventsUseCase.Result.Error -> {
                    it.copy(
                        showLoading = false,
                        errorMessage = useCaseResult.error.message,
                    )
                }
            }
        }
    }

    recentMatchesFlow.collect { useCaseResult ->
        stateManager.updateScreen(FeedViewState::class) {
            when (useCaseResult) {
                is GetRecentMatchesUseCase.Result.Success -> {
                    it.copy(
                        showLoading = false,
                        recentMatches = useCaseResult.matches,
                    )
                }
                is GetRecentMatchesUseCase.Result.Error -> {
                    it.copy(
                        showLoading = false,
                        errorMessage = useCaseResult.error.message,
                    )
                }
            }
        }
    }
}
