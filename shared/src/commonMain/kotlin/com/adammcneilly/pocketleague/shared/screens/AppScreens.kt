package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.feature.feed.FeedScreen
import com.adammcneilly.pocketleague.shared.screens.eventdetail.EventDetailScreen
import com.adammcneilly.pocketleague.shared.screens.eventstagedetail.EventStageDetailScreen
import com.adammcneilly.pocketleague.shared.screens.matchdetail.MatchDetailScreen
import com.adammcneilly.pocketleague.shared.screens.records.RecordsScreen
import com.adammcneilly.pocketleague.shared.screens.stats.StatsScreen

/**
 * An enumeration of all screens that appear somewhere in our application.
 */
enum class AppScreens(
    val getScreen: (StateManager) -> Screen,
) {
    Feed(
        getScreen = { stateManager ->
            stateManager.getFeedScreen()
        },
    ),
    Stats(
        getScreen = {
            StatsScreen
        },
    ),
    Records(
        getScreen = {
            RecordsScreen
        },
    ),
    MatchDetail(
        getScreen = {
            MatchDetailScreen
        },
    ),
    EventDetail(
        getScreen = {
            EventDetailScreen
        },
    ),
    EventStageDetail(
        getScreen = {
            EventStageDetailScreen
        },
    ),
}

fun StateManager.getFeedScreen(): Screen {
    return FeedScreen(
        eventService = this.repository.eventService,
        matchService = this.repository.matchService,
        // In the future we should put this event processor
        // elsewhere to avoid deep deep nesting in this file.
        eventProcessor = { event ->
            when (event) {
                is com.adammcneilly.pocketleague.feature.feed.FeedScreenEvents.RecentMatchesStateChanged -> {
                    this.updateScreen(com.adammcneilly.pocketleague.feature.feed.FeedViewState::class) { currentState ->
                        currentState.copy(
                            recentMatchesState = event.dataState,
                        )
                    }
                }
                is com.adammcneilly.pocketleague.feature.feed.FeedScreenEvents.OngoingEventsStateChanged -> {
                    this.updateScreen(com.adammcneilly.pocketleague.feature.feed.FeedViewState::class) { currentState ->
                        currentState.copy(
                            ongoingEventsState = event.dataState,
                        )
                    }
                }
            }
        }
    )
}
