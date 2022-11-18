package com.adammcneilly.pocketleague.shared.screens.teamselection

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * User friendly representation of the UI for the team selection screen.
 */
data class TeamSelectionViewState(
    val activeTeamsDataState: DataState<List<TeamOverviewDisplayModel>> = DataState.Loading,
    private val favoriteTeams: List<TeamOverviewDisplayModel> = emptyList(),
) : ScreenState {

    override val title: String? = null

    val teams: List<TeamOverviewDisplayModel>?
        get() = when (activeTeamsDataState) {
            is DataState.Error -> {
                // Error handling coming soon
                null
            }
            DataState.Loading -> {
                List(3) {
                    TeamOverviewDisplayModel.placeholder
                }
            }
            is DataState.Success -> {
                activeTeamsDataState.data
            }
        }

    /**
     * Given some [team], check if a team with the same ID exists in our [favoriteTeams] list.
     */
    fun isFavorite(team: TeamOverviewDisplayModel): Boolean {
        return favoriteTeams.any { favoriteTeam ->
            favoriteTeam.teamId == team.teamId
        }
    }
}
