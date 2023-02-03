package com.adammcneilly.pocketleague.shared.screens.teamselection

import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.feature.ScreenState

/**
 * User friendly representation of the UI for the team selection screen.
 */
data class TeamSelectionViewState(
    val teams: List<TeamOverviewDisplayModel> = emptyList()
) : ScreenState {

    override val title: String? = null
}
