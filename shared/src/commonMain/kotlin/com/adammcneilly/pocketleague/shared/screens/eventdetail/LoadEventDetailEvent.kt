package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.DataState
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
    val repoResult = appModule
        .dataModule
        .eventService
        .fetchEventParticipants(eventId)

    stateManager.updateScreen(EventDetailViewState::class) { currentState ->
        currentState.copy(
            participantsState = repoResult.map { teamList ->
                teamList.map(Team::toOverviewDisplayModel)
            },
        )
    }
}

private suspend fun Events.fetchEventDetail(
    eventId: String,
) {
    appModule
        .dataModule
        .eventService
        .getEvent(eventId)
        .collect { event ->
            stateManager.updateScreen(EventDetailViewState::class) { currentState ->
                currentState.copy(
                    eventDetailState = DataState.Success(event.toDetailDisplayModel()),
                )
            }
        }
}
