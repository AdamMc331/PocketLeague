package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.game.MatchGamesRequest
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Requests the games for the given [matchId].
 */
fun Events.loadMatchDetail(matchId: Match.Id) = screenCoroutine {
    fetchMatchDetail(matchId, it)

    fetchGames(matchId)
}

private suspend fun Events.fetchGames(matchId: Match.Id) {
    val repoResult = appModule
        .dataModule
        .gameService
        .fetchGamesForMatch(MatchGamesRequest(matchId))

    stateManager.updateScreen(MatchDetailViewState::class) { currentState ->
        currentState.copy(
            games = repoResult.getOrNull()?.map(Game::toDetailDisplayModel).orEmpty(),
        )
    }
}

private fun Events.fetchMatchDetail(
    matchId: Match.Id,
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .matchRepository
        .getMatchDetail(matchId)
        .onEach { match ->
            stateManager.updateScreen(MatchDetailViewState::class) { currentState ->
                currentState.copy(
                    matchDetail = match.toDetailDisplayModel(),
                )
            }
        }
        .launchIn(scope)
}
