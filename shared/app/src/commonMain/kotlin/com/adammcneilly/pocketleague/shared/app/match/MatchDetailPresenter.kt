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
import com.adammcneilly.pocketleague.data.game.MatchGamesRequest
import com.adammcneilly.pocketleague.data.game.OctaneGGGameService
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
import com.slack.circuit.runtime.presenter.Presenter

/**
 * State management container for the [MatchDetailScreen].
 */
class MatchDetailPresenter(
    private val matchId: Match.Id,
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
            match = OctaneGGMatchService()
                .getMatchDetail(matchId)
                .getOrNull()
                ?.toDetailDisplayModel()
                ?: MatchDetailDisplayModel.placeholder

            games = OctaneGGGameService()
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
            }
        }
    }
}
