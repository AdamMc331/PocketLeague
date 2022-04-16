package com.adammcneilly.pocketleague.shared.screens.feed

import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.Screen.Feed] screen.
 */
fun Navigation.initFeed(): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Feed",
        initState = {
            FeedState()
        },
        callOnInit = {
            // Coming soon?
        },
        reInitOnEachNavigation = true,
    )
}
