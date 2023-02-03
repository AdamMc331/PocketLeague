package com.adammcneilly.pocketleague.shared.screens.myteams

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.feature.ScreenState

/**
 * Defines the UI for the "My Teams" screen.
 */
data class MyTeamsViewState(
    val teams: List<TeamOverviewDisplayModel> = emptyList(),
    val recentMatches: List<MatchDetailDisplayModel> = emptyList()
) : ScreenState {

    override val title: String? = null
}
