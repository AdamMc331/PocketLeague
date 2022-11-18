package com.adammcneilly.pocketleague.shared.screens.teamselection

import com.adammcneilly.pocketleague.shared.screens.Navigation
import com.adammcneilly.pocketleague.shared.screens.ScreenInitSettings

/**
 * Provides the initialization information for selecting favorite teams.
 */
fun Navigation.initTeamSelection(): ScreenInitSettings {
    return ScreenInitSettings(
        title = "Select Favorites",
        initState = {
            TeamSelectionViewState()
        },
        callOnInit = {
            events.loadTeamSelection()
        },
        reInitOnEachNavigation = false,
    )
}
