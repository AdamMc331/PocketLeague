package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.game.MatchGamesRequest
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.delay

/**
 * Requests the games for the given [matchId].
 */
fun Events.loadMatchDetail(matchId: String) = screenCoroutine {
    delay(5_000)

    fetchMatchDetail(matchId)

    fetchGames(matchId)
}

private suspend fun Events.fetchGames(matchId: String) {
    val repoResult = repository.gameService.fetchGamesForMatch(MatchGamesRequest(matchId))

    stateManager.updateScreen(MatchDetailViewState::class) { currentState ->
        currentState.copy(
            gamesState = repoResult.map { gameList ->
                gameList.map(Game::toDetailDisplayModel)
            },
        )
    }
}

private suspend fun Events.fetchMatchDetail(matchId: String) {
    val repoResult = repository.matchService.fetchMatchDetail(matchId)

    stateManager.updateScreen(MatchDetailViewState::class) { currentState ->
        currentState.copy(
            matchDetailState = repoResult.map(Match::toDetailDisplayModel),
        )
    }
}
