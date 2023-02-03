package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailsByDateDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Loads the information necessary for the event stage detail screen.
 */
fun Events.loadEventStageDetail(
    eventId: String,
    stageId: String
) = screenCoroutine {
    fetchMatchesForStage(eventId, stageId, it)
}

private fun Events.fetchMatchesForStage(
    eventId: String,
    stageId: String,
    scope: CoroutineScope
) {
    appModule
        .dataModule
        .matchRepository
        .getMatchesForEventStage(
            eventId = eventId,
            stageId = stageId
        )
        .onEach { matchList ->
            val sortedMatches = matchList.sortedBy(Match::dateUTC)
            val displayModels = sortedMatches.map(Match::toDetailDisplayModel)
            val matchesByDate = displayModels.groupBy(MatchDetailDisplayModel::localDate)
            val matchesByDateDisplayModel = MatchDetailsByDateDisplayModel(matchesByDate)

            stateManager.updateScreen(EventStageDetailViewState::class) { currentState ->
                currentState.copy(
                    matchesDataState = matchesByDateDisplayModel
                )
            }
        }
        .launchIn(scope)
}
