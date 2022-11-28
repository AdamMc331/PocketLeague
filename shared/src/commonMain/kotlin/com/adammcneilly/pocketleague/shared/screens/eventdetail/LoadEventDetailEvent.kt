package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Load detailed information about a given [eventId].
 */
fun Events.loadEventDetail(
    eventId: String,
) = screenCoroutine {
    fetchEventDetail(eventId)

    fetchEventParticipants(eventId, it)
}

private fun Events.fetchEventParticipants(
    eventId: String,
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .eventService
        .getEventParticipants(eventId)
        .onEach { teamList ->
            stateManager.updateScreen(EventDetailViewState::class) { currentState ->
                currentState.copy(
                    participantsState = DataState.Success(teamList.map(Team::toOverviewDisplayModel)),
                )
            }
        }
        .launchIn(scope)
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
