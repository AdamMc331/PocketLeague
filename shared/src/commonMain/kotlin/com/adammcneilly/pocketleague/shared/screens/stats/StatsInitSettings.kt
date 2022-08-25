package com.adammcneilly.pocketleague.shared.screens.stats

import com.adammcneilly.pocketleague.feature.core.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.AppScreens.Stats] screen.
 */
fun initStats(): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Stats",
        initState = {
            StatsViewState()
        },
        callOnInit = {
            // Coming soon.
        },
        reInitOnEachNavigation = false,
    )
}
