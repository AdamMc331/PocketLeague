package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.Screens.Feed] screen.
 */
fun Navigation.initFeed(): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Feed",
        initState = {
            FeedViewState()
        },
        callOnInit = {
            events.loadFeed()
        },
        reInitOnEachNavigation = false,
    )
}
