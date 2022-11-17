package com.adammcneilly.pocketleague.shared.screens.teamselection

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

data class TeamSelectionViewState(
    val teamsDataState: DataState<List<TeamOverviewDisplayModel>> = DataState.Loading,
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
}
