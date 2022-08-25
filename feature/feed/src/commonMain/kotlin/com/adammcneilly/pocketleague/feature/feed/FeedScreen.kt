package com.adammcneilly.pocketleague.feature.feed

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.match.MatchService
import com.adammcneilly.pocketleague.feature.core.Screen
import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings
import com.adammcneilly.pocketleague.feature.core.ScreenParams

class FeedScreen(
    eventService: EventService,
    matchService: MatchService,
    eventProcessor: (FeedScreenEvents) -> Unit,
) : Screen {
    override val asString: String = "feed"

    override val navigationLevel: Int = 1

    override val initSettings: (ScreenParams) -> ScreenInitSettings = {
        initFeed(
            eventService,
            matchService,
            eventProcessor,
        )
    }

    override val stackableInstances: Boolean = true
}
