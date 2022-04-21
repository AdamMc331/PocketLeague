package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect

/**
 * Requests the games for the given [matchId].
 */
fun Events.getGamesForMatch(matchId: String) = screenCoroutine {
    val useCase = this.dependencies.getMatchGamesUseCase

    useCase.invoke(matchId).collect { useCaseResult ->
        stateManager.updateScreen(MatchDetailViewState::class) {
            when (useCaseResult) {
                is DataState.Loading -> {
                    it.copy(
                        showLoading = true,
                    )
                }
                is DataState.Success -> {
                    it.copy(
                        showLoading = false,
                        games = useCaseResult.data,
                    )
                }
                is DataState.Error -> {
                    it.copy(
                        showLoading = false,
                        errorMessage = useCaseResult.error.message,
                    )
                }
            }
        }
    }
}
