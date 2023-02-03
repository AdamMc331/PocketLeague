package com.adammcneilly.pocketleague.shared.screens.stats

import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for our [com.adammcneilly.pocketleague.shared.screens.Screens.Stats] screen.
 */
fun Navigation.initStats(): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Stats",
        initState = {
            StatsViewState()
        },
        callOnInit = {
            // Coming soon.
        },
        reInitOnEachNavigation = false
    )
}
