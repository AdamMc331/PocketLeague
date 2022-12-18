package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventParticipants
import com.adammcneilly.pocketleague.data.octanegg.models.toEvent
import com.adammcneilly.pocketleague.data.octanegg.models.toTeam
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock

/**
 * A concrete implementation of [EventRepository] that requests data
 * from the remote [apiClient].
 */
class OctaneGGEventRepository(
    private val apiClient: BaseKTORClient,
) : EventRepository {

    constructor() : this(OctaneGGAPIClient)

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return flow {
            val apiResponse = apiClient.getResponse<OctaneGGEventListResponse>(
                endpoint = EVENTS_ENDPOINT,
                params = mapOf(
                    "after" to Clock.System.now().toString(),
                ),
            ).map { eventListResponse ->
                eventListResponse.events?.map(OctaneGGEvent::toEvent).orEmpty()
            }

            // Need to handle an error
            val events = (apiResponse as? DataState.Success)?.data.orEmpty()
            emit(events)
        }
    }

    override fun getEvent(eventId: String): Flow<Event> {
        return flow {
            val endpoint = "$EVENTS_ENDPOINT/$eventId"

            val apiResponse = apiClient.getResponse<OctaneGGEvent>(
                endpoint = endpoint,
            ).map { octaneEvent ->
                octaneEvent.toEvent()
            }

            // Need to handle error here.
            (apiResponse as? DataState.Success)?.data?.let { event ->
                emit(event)
            }
        }
    }

    override fun getEventParticipants(eventId: String): Flow<List<Team>> {
        return flow {
            val endpoint = "$EVENTS_ENDPOINT/$eventId/participants"

            val apiResponse = apiClient.getResponse<OctaneGGEventParticipants>(
                endpoint = endpoint,
            ).map { octaneEventParticipants ->
                octaneEventParticipants.participants.map {
                    it.toTeam()
                }
            }

            // Need to handle error
            val teams = (apiResponse as? DataState.Success)?.data.orEmpty()
            emit(teams)
        }
    }

    override fun getOngoingEvents(): Flow<List<Event>> {
        return flow {
            val apiResponse = apiClient.getResponse<OctaneGGEventListResponse>(
                endpoint = EVENTS_ENDPOINT,
                params = mapOf(
                    "date" to Clock.System.now().toString(),
                ),
            ).map { eventListResponse ->
                eventListResponse.events?.map(OctaneGGEvent::toEvent).orEmpty()
            }

            // Need to handle an error
            val events = (apiResponse as? DataState.Success)?.data.orEmpty()
            emit(events)
        }
    }

    override suspend fun insertEvents(events: List<Event>) {
        throw UnsupportedOperationException("Inserting events is not supported by the octane.gg API.")
    }

    override suspend fun insertEventParticipants(teams: List<Team>, eventId: String) {
        throw UnsupportedOperationException("Inserting teams is not supported by the octane.gg API.")
    }

    companion object {
        const val EVENTS_ENDPOINT = "/events"
    }
}
