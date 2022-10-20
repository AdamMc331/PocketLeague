package com.adammcneilly.pocketleague.shared.screens.myteams

import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.testTeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI for the "My Teams" screen.
 */
data class MyTeamsViewState(
    val isLoading: Boolean = true,
    val teams: List<TeamOverviewDisplayModel> = List(3) {
        testTeamOverviewDisplayModel
    },
) : ScreenState
