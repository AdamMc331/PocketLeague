package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailsByDateDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.MatchListRequest
import com.adammcneilly.pocketleague.shared.screens.Events

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

    val repoResult = appModule
        .dataModule
        .matchService
        .fetchMatches(stageMatchesRequest)

    stateManager.updateScreen(EventStageDetailViewState::class) { currentState ->
        currentState.copy(
            matchesDataState = repoResult.map { matchList ->
                val sortedMatches = matchList.sortedBy(Match::dateUTC)
                val displayModels = sortedMatches.map(Match::toDetailDisplayModel)
                val matchesByDate = displayModels.groupBy(MatchDetailDisplayModel::localDate)
                MatchDetailsByDateDisplayModel(matchesByDate)
            }
        )
    }
}
