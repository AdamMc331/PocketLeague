package com.adammcneilly.pocketleague.feature.feed

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.match.MatchService
import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.AppScreens.Feed] screen.
 */
fun initFeed(
    eventService: EventService,
    matchService: MatchService,
    eventProcessor: (FeedScreenEvent) -> Unit,
): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Feed",
        initState = {
            FeedViewState()
        },
        callOnInit = {
            loadFeed(
                eventService,
                matchService,
                eventProcessor,
            )
        },
        reInitOnEachNavigation = false,
    )
}
