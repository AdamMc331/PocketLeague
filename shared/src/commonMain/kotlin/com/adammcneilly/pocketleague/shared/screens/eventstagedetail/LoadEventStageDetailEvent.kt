package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.MatchListRequest
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect

/**
 * Loads the information necessary for the event stage detail screen.
 */
fun Events.loadEventStageDetail(
    eventId: String,
    stageId: String,
) = screenCoroutine {
    fetchMatchesForStage(eventId, stageId)
}

private suspend fun Events.fetchMatchesForStage(
    eventId: String,
    stageId: String,
) {
    val stageMatchesRequest = MatchListRequest(
        eventId = eventId,
        stageId = stageId,
    )

    repository.matchRepository.fetchMatches(stageMatchesRequest).collect { repoResult ->
        stateManager.updateScreen(EventStageDetailViewState::class) {
            val viewState = when (repoResult) {
                is DataState.Loading -> {
                    it.copy(
                        showLoading = true,
                    )
                }
                is DataState.Success -> {
                    val mappedMatches = repoResult.data.map(Match::toDetailDisplayModel)

                    it.copy(
                        showLoading = false,
                        matches = mappedMatches,
                    )
                }
                is DataState.Error -> {
                    it.copy(
                        errorMessage = repoResult.error.message,
                        showLoading = false,
                    )
                }
            }

            viewState
        }
    }
}
