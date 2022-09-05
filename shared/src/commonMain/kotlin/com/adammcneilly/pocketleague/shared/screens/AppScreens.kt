package com.adammcneilly.pocketleague.shared.screens

import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.feature.core.ScreenParams
import com.adammcneilly.pocketleague.feature.eventdetail.EventDetailParams
import com.adammcneilly.pocketleague.shared.screens.eventdetail.EventDetailScreenBuilder
import com.adammcneilly.pocketleague.shared.screens.eventstagedetail.EventStageDetailScreen
import com.adammcneilly.pocketleague.shared.screens.feed.FeedScreenBuilder
import com.adammcneilly.pocketleague.shared.screens.matchdetail.MatchDetailScreen
import com.adammcneilly.pocketleague.shared.screens.records.RecordsScreen
import com.adammcneilly.pocketleague.shared.screens.stats.StatsScreen

/**
 * An enumeration of all screens that appear somewhere in our application.
 *
 * By using an enum, we can have an exhaustive list of possible screens, as well as a quick way for
 * us to specify which screen we want to navigate to.
 *
 * We should note, though, that some [Screen] entities may require dependencies that can only be
 * provided via our [StateManager]. As a result, each possible [AppScreens] will provide a [getScreen]
 * function to use our state manager to pull those dependencies and generate our [Screen] entity.
 */
enum class AppScreens(
    val getScreen: (ScreenParams?, StateManager) -> Screen,
) {
    Feed(
        getScreen = { _, stateManager ->
            FeedScreenBuilder.build(stateManager)
        }
    ),
    Stats(
        getScreen = { _, _ ->
            StatsScreen
        },
    ),
    Records(
        getScreen = { _, _ ->
            RecordsScreen
        },
    ),
    MatchDetail(
        getScreen = { _, _ ->
            MatchDetailScreen
        },
    ),
    EventDetail(
        getScreen = { params, stateManager ->
            EventDetailScreenBuilder.build(
                params as EventDetailParams,
                stateManager,
            )
        },
    ),
    EventStageDetail(
        getScreen = { _, _ ->
            EventStageDetailScreen
        },
    ),
}
