package com.adammcneilly.pocketleague.shared.app.match

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.game.MatchGamesRequest
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.adammcneilly.pocketleague.feature.teamdetail.TeamDetailScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * State management container for the [MatchDetailScreen].
 */
class MatchDetailPresenter(
    private val matchId: Match.Id,
    private val gameService: GameService,
    private val matchRepository: MatchRepository,
    private val navigator: Navigator,
) : Presenter<MatchDetailScreen.State> {

    @Composable
    override fun present(): MatchDetailScreen.State {
        var match by remember {
            mutableStateOf(MatchDetailDisplayModel.placeholder)
        }

        var games by remember {
            mutableStateOf(
                listOf(
                    GameDetailDisplayModel.placeholder,
                    GameDetailDisplayModel.placeholder,
                    GameDetailDisplayModel.placeholder,
                ),
            )
        }

        var selectedGame by remember {
            mutableStateOf<GameDetailDisplayModel?>(null)
        }

        LaunchedEffect(Unit) {
            matchRepository
                .getMatchDetail(matchId)
                .map(Match::toDetailDisplayModel)
                .onEach { displayModel ->
                    match = displayModel
                }
                .launchIn(this)

            games = gameService
                .fetchGamesForMatch(MatchGamesRequest(matchId))
                .getOrNull()
                ?.map(Game::toDetailDisplayModel)
                .orEmpty()
        }

        return MatchDetailScreen.State(
            match = match,
            selectedGame = selectedGame,
            games = games,
        ) { event ->
            when (event) {
                is MatchDetailScreen.Event.GameSelected -> {
                    selectedGame = event.game
                }
                MatchDetailScreen.Event.SelectedGameDismissed -> {
                    selectedGame = null
                }
                is MatchDetailScreen.Event.TeamClicked -> {
                    navigator.goTo(TeamDetailScreen(event.teamId))
                }
            }
        }
    }
}
