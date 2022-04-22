package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect

/**
 * Requests the games for the given [matchId].
 */
fun Events.getGamesForMatch(matchId: String) = screenCoroutine {
    val useCase = this.dependencies.getMatchGamesUseCase

    useCase.invoke(matchId).collect { useCaseResult ->
        stateManager.updateScreen(MatchDetailViewState::class) {
            it.copy(
                gamesDataState = useCaseResult,
            )
        }
    }
}
