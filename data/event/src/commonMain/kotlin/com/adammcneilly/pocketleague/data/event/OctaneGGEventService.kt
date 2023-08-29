package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventParticipants
import com.adammcneilly.pocketleague.data.octanegg.models.toEvent
import com.adammcneilly.pocketleague.data.octanegg.models.toTeam
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient

/**
 * A concrete implementation of [EventRepository] that requests data
 * from the remote [apiClient].
 */
class OctaneGGEventService(
    private val apiClient: BaseKTORClient,
    private val timeProvider: TimeProvider,
) : RemoteEventService {

    override suspend fun getUpcomingEvents(): Result<List<Event>> {
        return apiClient.getResponse<OctaneGGEventListResponse>(
            endpoint = EVENTS_ENDPOINT,
            params = mapOf(
                "after" to timeProvider.now().toString(),
                "group" to "rlcs",
            ),
        ).map { eventListResponse ->
            eventListResponse.events?.map(OctaneGGEvent::toEvent).orEmpty()
        }
    }

    override suspend fun getEvent(eventId: Event.Id): Result<Event> {
        val endpoint = "$EVENTS_ENDPOINT/${eventId.id}"

        return apiClient.getResponse<OctaneGGEvent>(
            endpoint = endpoint,
        ).map { octaneEvent ->
            octaneEvent.toEvent()
        }
    }

    override suspend fun getEventParticipants(eventId: Event.Id): Result<List<Team>> {
        val endpoint = "$EVENTS_ENDPOINT/${eventId.id}/participants"

        return apiClient.getResponse<OctaneGGEventParticipants>(
            endpoint = endpoint,
        ).map { octaneEventParticipants ->
            octaneEventParticipants.participants.map {
                it.toTeam()
            }
        }
    }

    override suspend fun getOngoingEvents(): Result<List<Event>> {
        return apiClient.getResponse<OctaneGGEventListResponse>(
            endpoint = EVENTS_ENDPOINT,
            params = mapOf(
                "date" to timeProvider.now().toString(),
                "group" to "rlcs",
            ),
        ).map { eventListResponse ->
            eventListResponse.events?.map(OctaneGGEvent::toEvent).orEmpty()
        }
    }

    companion object {
        const val EVENTS_ENDPOINT = "/events"
    }
}
