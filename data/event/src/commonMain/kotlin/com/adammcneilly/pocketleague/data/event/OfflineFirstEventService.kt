package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.local.PocketLeagueDatabase
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventParticipants
import com.adammcneilly.pocketleague.data.octanegg.models.toEvent
import com.adammcneilly.pocketleague.data.octanegg.models.toTeam
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import com.adammcneilly.pocketleague.data.remote.RemoteParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.datetime.Clock

/**
 * An implementation of [EventService] that only returns data from the supplied [database]
 * but uses the given [apiClient] to [sync] data.
 */
class OfflineFirstEventService(
    private val database: PocketLeagueDatabase,
    private val apiClient: BaseKTORClient,
) : EventService {

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

    override fun getEvent(eventId: String): Flow<Event> {
        return database
            .getEvent(eventId)
            .onStart {
                fetchAndPersistEvent(eventId)
            }
    }

    private suspend fun fetchAndPersistEvent(eventId: String) {
        val endpoint = "$EVENTS_ENDPOINT/$eventId"

        val apiResponse = apiClient.getResponse<OctaneGGEvent>(
            endpoint = endpoint,
        ).map { octaneEvent ->
            octaneEvent.toEvent()
        }

        when (apiResponse) {
            is DataState.Error -> {
                // Idk?
            }

            DataState.Loading -> {
                // Idk?
            }

            is DataState.Success -> {
                database.storeEvents(listOf(apiResponse.data))
            }
        }
    }

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return database
            .getUpcomingEvents()
            .onStart {
                fetchAndPersistUpcomingRLCSEvents()
            }
    }

    override fun getOngoingEvents(): Flow<List<Event>> {
        return database
            .getOngoingEvents()
            .onStart {
                fetchAndPersistOngoingRLCSEvents()
            }
    }

    private suspend fun fetchAndPersistOngoingRLCSEvents() {
        val ongoingRlcsEventsRequest = EventListRequest(
            group = "rlcs",
            date = Clock.System.now(),
        )

        val ongoingRlcsEventsResponse = fetchEvents(ongoingRlcsEventsRequest)

        when (ongoingRlcsEventsResponse) {
            is DataState.Error -> {
                // ARM - DO WE NEED THIS?
            }

            DataState.Loading -> {
                // ARM - DO WE NEED THIS?
            }

            is DataState.Success -> {
                database.storeEvents(ongoingRlcsEventsResponse.data)
            }
        }
    }

    private suspend fun fetchAndPersistUpcomingRLCSEvents() {
        val upcomingRlcsEventsRequest = EventListRequest(
            group = "rlcs",
            after = Clock.System.now(),
        )

        val upcomingRlcsEventsResponse = fetchEvents(upcomingRlcsEventsRequest)

        when (upcomingRlcsEventsResponse) {
            is DataState.Error -> {
                // ARM - DO WE NEED THIS?
            }

            DataState.Loading -> {
                // ARM - DO WE NEED THIS?
            }

            is DataState.Success -> {
                database.storeEvents(upcomingRlcsEventsResponse.data)
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
