package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.feature.event.detail.EventDetailViewState
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
    fetchEventDetail(eventId, it)

    fetchEventParticipants(eventId, it)
}

private fun Events.fetchEventParticipants(
    eventId: String,
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .eventRepository
        .getEventParticipants(eventId)
        .onEach { teamList ->
            stateManager.updateScreen(EventDetailViewState::class) { currentState ->
                currentState.copy(
                    participants = teamList.map(Team::toOverviewDisplayModel),
                )
            }
        }
        .launchIn(scope)
}

private fun Events.fetchEventDetail(
    eventId: String,
    scope: CoroutineScope,
) {
    appModule
        .dataModule
        .eventRepository
        .getEvent(eventId)
        .onEach { event ->
            stateManager.updateScreen(EventDetailViewState::class) { currentState ->
                currentState.copy(
                    eventDetail = event.toDetailDisplayModel(),
                )
            }
        }
        .launchIn(scope)
}
