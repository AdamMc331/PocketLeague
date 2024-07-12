package com.adammcneilly.pocketleague.feature.teamdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import com.adammcneilly.pocketleague.data.match.api.MatchRepository
import com.adammcneilly.pocketleague.data.player.PlayerRepository
import com.adammcneilly.pocketleague.data.team.TeamRepository
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

private const val DAYS_PER_WEEK = 7

internal class TeamDetailPresenter(
    private val teamId: String,
    private val teamRepository: TeamRepository,
    private val playerRepository: PlayerRepository,
    private val matchRepository: MatchRepository,
    private val timeProvider: TimeProvider,
) : Presenter<TeamDetailScreen.State> {
    @Composable
    override fun present(): TeamDetailScreen.State {
        var team by remember {
            mutableStateOf(TeamOverviewDisplayModel.placeholder)
        }

        var roster by remember {
            mutableStateOf(emptyList<PlayerDisplayModel>())
        }

        var recentMatches by remember {
            mutableStateOf(emptyList<MatchDetailDisplayModel>())
        }

        LaunchedEffect(Unit) {
            teamRepository
                .getTeamById(teamId)
                .map(Team::toOverviewDisplayModel)
                .onEach { displayModel ->
                    team = displayModel
                }
                .launchIn(this)

            playerRepository
                .getPlayersForTeam(teamId)
                .map { players ->
                    players.map(Player::toDisplayModel)
                }
                .onEach { players ->
                    roster = players
                }
                .launchIn(this)

            // Come back and put in real dates PLUS team ID.
            val matchRequest = MatchListRequest.DateRange(
                startDateUTC = timeProvider.daysAgo(DAYS_PER_WEEK),
                endDateUTC = timeProvider.now(),
                teamId = teamId,
            )

            matchRepository
                .stream(matchRequest)
                .map { matches ->
                    matches.map { match ->
                        match.toDetailDisplayModel(timeProvider)
                    }
                }
                .onEach { matches ->
                    recentMatches = matches
                }
                .launchIn(this)
        }

        return TeamDetailScreen.State(
            team = team,
            roster = roster,
            recentMatches = recentMatches,
        ) { event ->
            // Handle UI events
        }
    }
}
