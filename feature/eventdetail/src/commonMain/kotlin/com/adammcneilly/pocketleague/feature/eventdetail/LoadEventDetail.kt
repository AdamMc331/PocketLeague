package com.adammcneilly.pocketleague.feature.eventdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.event.EventService

suspend fun loadEventDetail(
    params: EventDetailParams,
    eventService: EventService,
    eventProcessor: (EventDetailScreenEvent) -> Unit,
) {
    fetchEventParticipants(params, eventService, eventProcessor)

    fetchEventDetail(params, eventService, eventProcessor)
}

private suspend fun fetchEventDetail(
    params: EventDetailParams,
    eventService: EventService,
    eventProcessor: (EventDetailScreenEvent) -> Unit,
) {
    val repoResult = eventService.fetchEvent(
        params.eventId,
    )

    when (repoResult) {
        DataState.Loading -> {
            // No-op
        }
        is DataState.Success -> {
            val mappedEvent = repoResult.data.toDetailDisplayModel()

            eventProcessor.invoke(EventDetailScreenEvent.LoadedEvent(mappedEvent))
        }
        is DataState.Error -> {
            eventProcessor.invoke(EventDetailScreenEvent.Error(repoResult.error))
        }
    }
}

private suspend fun fetchEventParticipants(
    params: EventDetailParams,
    eventService: EventService,
    eventProcessor: (EventDetailScreenEvent) -> Unit,
) {
    val repoResult = eventService.fetchEventParticipants(
        params.eventId,
    )

    when (repoResult) {
        DataState.Loading -> {
            // No Op
        }
        is DataState.Success -> {
            val mappedTeams = repoResult.data.map(Team::toOverviewDisplayModel)

            eventProcessor.invoke(EventDetailScreenEvent.LoadedParticipants(mappedTeams))
        }
        is DataState.Error -> {
            eventProcessor.invoke(EventDetailScreenEvent.Error(repoResult.error))
        }
    }
}
