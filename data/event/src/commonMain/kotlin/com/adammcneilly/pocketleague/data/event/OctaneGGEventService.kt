package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventParticipants
import com.adammcneilly.pocketleague.data.octanegg.models.toEvent
import com.adammcneilly.pocketleague.data.octanegg.models.toTeam
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import com.adammcneilly.pocketleague.data.remote.RemoteParams

/**
 * A concrete implementation of [EventService] that requests data through the
 * given [apiClient].
 */
class OctaneGGEventService(
    private val apiClient: BaseKTORClient,
) : EventService {

    constructor() : this(OctaneGGAPIClient)

    override suspend fun fetchEvents(request: EventListRequest): DataState<List<Event>> {
        return apiClient.getResponse<OctaneGGEventListResponse>(
            endpoint = EVENTS_ENDPOINT,
            params = request.toOctaneParams(),
        ).map { eventListResponse ->
            eventListResponse.events?.map(OctaneGGEvent::toEvent).orEmpty()
        }
    }

    override suspend fun fetchEvent(eventId: String): DataState<Event> {
        val endpoint = "$EVENTS_ENDPOINT/$eventId"

        return apiClient.getResponse<OctaneGGEvent>(
            endpoint = endpoint,
        ).map { octaneEvent ->
            octaneEvent.toEvent()
        }
    }

    override suspend fun fetchEventParticipants(eventId: String): DataState<List<Team>> {
        val endpoint = "$EVENTS_ENDPOINT/$eventId/participants"

        return apiClient.getResponse<OctaneGGEventParticipants>(
            endpoint = endpoint,
        ).map { octaneEventParticipants ->
            octaneEventParticipants.participants.map {
                it.toTeam()
            }
        }
    }

    companion object {
        private const val EVENTS_ENDPOINT = "/events"
    }
}

private fun EventListRequest.toOctaneParams(): RemoteParams {
    return mapOf(
        "group" to group,
        "tier" to tiers,
        "after" to after.toString(),
        "before" to before.toString(),
        "date" to date.toString(),
        "name" to name,
    )
}
