package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.data.game.MatchGamesRequest
import com.adammcneilly.pocketleague.shared.screens.Events

/**
 * Requests the games for the given [matchId].
 */
fun Events.loadMatchDetail(matchId: String) = screenCoroutine {
    fetchMatchDetail(matchId)

    fetchGames(matchId)
}

private suspend fun Events.fetchGames(matchId: String) {
    val repoResult = repository.gameService.fetchGamesForMatch(MatchGamesRequest(matchId))

    stateManager.updateScreen(MatchDetailViewState::class) {
        val viewState = when (repoResult) {
            is DataState.Success -> {
                it.copy(
                    games = repoResult.data.map(Game::toDetailDisplayModel),
                    showGamesLoading = false,
                )
            }
            DataState.Loading -> {
                it.copy(
                    showGamesLoading = true,
                )
            }
            is DataState.Error -> {
                it.copy(
                    showGamesLoading = false,
                    gamesErrorMessage = repoResult.error.message,
                )
            }
        }

        viewState
    }
}

private suspend fun Events.fetchMatchDetail(matchId: String) {
    val repoResult = repository.matchService.fetchMatchDetail(matchId)

    stateManager.updateScreen(MatchDetailViewState::class) {
        val viewState = when (repoResult) {
            is DataState.Loading -> {
                it.copy(
                    showDetailLoading = true,
                )
            }
            is DataState.Success -> {
                it.copy(
                    showDetailLoading = false,
                    matchDetail = repoResult.data.toDetailDisplayModel(),
                )
            }
            is DataState.Error -> {
                it.copy(
                    showDetailLoading = false,
                    detailInfoErrorMessage = repoResult.error.message,
                )
            }
        }

        viewState
    }
}
