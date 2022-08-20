package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.MatchListRequest
import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect
import kotlinx.datetime.TimeZone

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
                    val sortedMatches = repoResult.data.sortedBy(Match::dateUTC)

                    it.copy(
                        showLoading = false,
                        matchesByDate = sortedMatches.groupByLocalDate(),
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

private const val MATCH_DATE_FORMAT = "MMM dd, yyyy"

private fun List<Match>.groupByLocalDate(): Map<String, List<MatchDetailDisplayModel>> {
    val dateTimeFormatter = dateTimeFormatter()

    val matchesByDate: Map<String, List<Match>> = this.groupBy { match ->
        match.dateUTC?.let { date ->
            dateTimeFormatter.formatUTCString(
                utcString = date,
                formatPattern = MATCH_DATE_FORMAT,
                timeZone = TimeZone.currentSystemDefault(),
            )
        }.orEmpty()
    }

    return matchesByDate.mapValues { (_, matchList) ->
        matchList.map(Match::toDetailDisplayModel)
    }
}
