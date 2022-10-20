package com.adammcneilly.pocketleague.shared.screens.myteams

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI for the "My Teams" screen.
 */
data class MyTeamsViewState(
    val teamsDataState: DataState<List<TeamOverviewDisplayModel>> = DataState.Loading,
) : ScreenState {

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
}
