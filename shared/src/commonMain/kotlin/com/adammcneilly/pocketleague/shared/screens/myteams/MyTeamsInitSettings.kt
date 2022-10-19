package com.adammcneilly.pocketleague.shared.screens.myteams

import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings

/**
 * Returns the [ScreenInitSettings] for
 * our [com.adammcneilly.pocketleague.shared.screens.Screens.MyTeams] screen.
 */
fun Navigation.initMyTeams(): ScreenInitSettings {
    return ScreenInitSettings(
        title = "My Teams",
        initState = {
            MyTeamsViewState()
        },
        callOnInit = {
            // Coming soon
            // events.loadFeed()
        },
        reInitOnEachNavigation = false,
    )
}
