package com.adammcneilly.pocketleague.shared.screens.myteams

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI for the "My Teams" screen.
 */
data class MyTeamsViewState(
    val teamsDataState: DataState<List<TeamOverviewDisplayModel>> = DataState.Loading,
    val recentMatchesDataState: DataState<List<MatchDetailDisplayModel>> = DataState.Loading
) : ScreenState {

    override val title: String? = null

    val teams: List<TeamOverviewDisplayModel>?
        get() = when (teamsDataState) {
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
                teamsDataState.data
            }
        }

    val recentMatches: List<MatchDetailDisplayModel>?
        get() = when (recentMatchesDataState) {
            is DataState.Error -> {
                null
            }
            DataState.Loading -> {
                List(3) {
                    MatchDetailDisplayModel.placeholder
                }
            }
            is DataState.Success -> {
                recentMatchesDataState.data
            }
        }
}
