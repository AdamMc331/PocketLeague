package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.slack.circuit.runtime.presenter.Presenter

internal class TeamDetailPresenter(
    private val teamId: String,
) : Presenter<TeamDetailScreen.State> {

    @Composable
    override fun present(): TeamDetailScreen.State {
        val team by remember {
            mutableStateOf(TeamOverviewDisplayModel.placeholder)
        }

        return TeamDetailScreen.State(
            team = team,
        ) { event ->
            // Handle UI events
        }
    }
}
