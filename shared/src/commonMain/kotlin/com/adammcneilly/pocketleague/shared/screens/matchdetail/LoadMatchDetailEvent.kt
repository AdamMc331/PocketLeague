package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.core.models.Match
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
    val repoResult = appModule
        .dataModule
        .gameService
        .fetchGamesForMatch(MatchGamesRequest(matchId))

    stateManager.updateScreen(MatchDetailViewState::class) { currentState ->
        currentState.copy(
            games = (repoResult as? DataState.Success)?.data?.map(Game::toDetailDisplayModel).orEmpty(),
        )
    }
}

private suspend fun Events.fetchMatchDetail(matchId: String) {
    val repoResult = appModule
        .dataModule
        .matchService
        .fetchMatchDetail(matchId)

    stateManager.updateScreen(MatchDetailViewState::class) { currentState ->
        currentState.copy(
            matchDetail = (repoResult as? DataState.Success)?.data?.let(Match::toDetailDisplayModel),
        )
    }
}
