package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.team.TeamRepository
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

internal class TeamDetailPresenter(
    private val teamId: String,
    private val teamRepository: TeamRepository,
) : Presenter<TeamDetailScreen.State> {

    @Composable
    override fun present(): TeamDetailScreen.State {
        var team by remember {
            mutableStateOf(TeamOverviewDisplayModel.placeholder)
        }

        var roster by remember {
            mutableStateOf(
                listOf(
                    PlayerDisplayModel(
                        id = "Alpha54",
                        tag = "AdamMc54",
                    ),
                    PlayerDisplayModel(
                        id = "Radosin",
                        tag = "Radosin",
                    ),
                    PlayerDisplayModel(
                        id = "Zen",
                        tag = "PleaZENtlyPlump",
                    ),
                ),
            )
        }

        LaunchedEffect(Unit) {
            teamRepository
                .getTeamById(teamId)
                .map(Team::toOverviewDisplayModel)
                .onEach { displayModel ->
                    team = displayModel
                }
                .launchIn(this)

            // TODO: Fetch roster
        }

        return TeamDetailScreen.State(
            team = team,
            roster = roster,
        ) { event ->
            // Handle UI events
        }
    }
}
