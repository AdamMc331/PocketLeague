package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.AppScreens.Feed] screen.
 */
fun initFeed(): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Feed",
        initState = {
            FeedViewState()
        },
        callOnInit = {
            // ARM - COMING SOON
//            events.loadFeed()
        },
        reInitOnEachNavigation = false,
    )
}
