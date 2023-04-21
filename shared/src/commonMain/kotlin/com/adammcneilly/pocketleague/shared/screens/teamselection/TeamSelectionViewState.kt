package com.adammcneilly.pocketleague.shared.screens.teamselection

import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.feature.ScreenState

/**
 * User friendly representation of the UI for the team selection screen.
 */
data class TeamSelectionViewState(
    val teams: List<TeamOverviewDisplayModel> = emptyList(),
    val selectedRegionName: String = "All Regions",
) : ScreenState {

    override val title: String? = null

    val filteredTeams: List<TeamOverviewDisplayModel>
        get() {
            return when (selectedRegionName) {
                "All Regions" -> teams
                else -> teams.filter { team ->
                    team.regionName == selectedRegionName
                }
            }
        }
}
