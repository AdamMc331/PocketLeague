package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.MatchGamesRequest
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect

/**
 * Requests the games for the given [matchId].
 */
fun Events.loadMatchDetail(matchId: String) = screenCoroutine {
    fetchMatchDetail(matchId)

    fetchGames(matchId)
}

private suspend fun Events.fetchGames(matchId: String) {
    repository.gameRepository.fetchGamesForMatch(MatchGamesRequest(matchId)).collect { repoResult ->
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
}

private suspend fun Events.fetchMatchDetail(matchId: String) {
    repository.matchRepository.fetchMatchDetail(matchId).collect { repoResult ->
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
}
