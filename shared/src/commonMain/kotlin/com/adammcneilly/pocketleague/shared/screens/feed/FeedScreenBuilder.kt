package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.feature.feed.FeedScreen
import com.adammcneilly.pocketleague.feature.feed.FeedScreenEvent
import com.adammcneilly.pocketleague.feature.feed.FeedViewState
import com.adammcneilly.pocketleague.shared.screens.StateManager

object FeedScreenBuilder {

    /**
     * Create an instance of a [FeedScreen] using the dependencies and
     * event handling provided by the given [stateManager].
     */
    fun build(stateManager: StateManager): FeedScreen {
        return FeedScreen(
            eventService = stateManager.repository.eventService,
            matchService = stateManager.repository.matchService,
            eventProcessor = stateManager::processFeedEvent,
        )
    }
}

private fun StateManager.processFeedEvent(event: FeedScreenEvent) {
    when (event) {
        is FeedScreenEvent.RecentMatchesStateChanged -> {
            this.updateScreen(FeedViewState::class) { currentState ->
                currentState.copy(
                    recentMatchesState = event.dataState,
                )
            }
        }
        is FeedScreenEvent.OngoingEventsStateChanged -> {
            this.updateScreen(FeedViewState::class) { currentState ->
                currentState.copy(
                    ongoingEventsState = event.dataState,
                )
            }
        }
    }
}
