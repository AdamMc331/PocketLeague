package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.shared.screens.Events

/**
 * Load detailed information about a given [eventId].
 */
fun Events.loadEventDetail(
    eventId: String,
) = screenCoroutine {
    fetchEventDetail(eventId)

    fetchEventParticipants(eventId)
}

private suspend fun Events.fetchEventParticipants(
    eventId: String,
) {
    val repoResult = repository.eventService.fetchEventParticipants(
        eventId,
    )

    stateManager.updateScreen(EventDetailViewState::class) { currentState ->
        currentState.copy(
            participantsState = repoResult.map { teamList ->
                teamList.map(Team::toOverviewDisplayModel)
            },
        )
    }
}

private suspend fun Events.fetchEventDetail(eventId: String) {
    val repoResult = repository.eventService.fetchEvent(
        eventId,
    )

    stateManager.updateScreen(EventDetailViewState::class) {
        val viewState = when (repoResult) {
            is DataState.Loading -> {
                it.copy(
                    showLoading = true,
                )
            }
            is DataState.Success -> {
                it.copy(
                    showLoading = false,
                    eventDetail = repoResult.data.toDetailDisplayModel()
                )
            }
            is DataState.Error -> {
                it.copy(
                    showLoading = false,
                    errorMessage = repoResult.error.message,
                )
            }
        }

        viewState
    }
}
