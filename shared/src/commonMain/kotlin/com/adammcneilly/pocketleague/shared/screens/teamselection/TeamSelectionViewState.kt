package com.adammcneilly.pocketleague.shared.screens.teamselection

import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.feature.ScreenState

/**
 * User friendly representation of the UI for the team selection screen.
 */
data class TeamSelectionViewState(
    val teams: List<TeamOverviewDisplayModel> = emptyList(),
    private val favoriteTeams: List<TeamOverviewDisplayModel> = emptyList(),
) : ScreenState {

    override val title: String? = null

    /**
     * Given some [team], check if a team with the same ID exists in our [favoriteTeams] list.
     */
    fun isFavorite(team: TeamOverviewDisplayModel): Boolean {
        return favoriteTeams.any { favoriteTeam ->
            favoriteTeam.teamId == team.teamId
        }
    }
}
