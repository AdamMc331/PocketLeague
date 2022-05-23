package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.data.models.MatchGamesRequest
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect

/**
 * Requests the games for the given [matchId].
 */
fun Events.getGamesForMatch(matchId: String) = screenCoroutine {
    val matchRequest = MatchGamesRequest(
        matchId = matchId,
    )

    repository.gameRepository.fetchGamesForMatch(matchRequest).collect { repoResult ->
        stateManager.updateScreen(MatchDetailViewState::class) {
            it.copy(
                gamesDataState = repoResult,
            )
        }
    }
}
