package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.local.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.mappers.toEvent
import com.adammcneilly.pocketleague.data.local.mappers.toLocalEvent
import com.adammcneilly.pocketleague.data.local.mappers.toLocalTeam
import com.adammcneilly.pocketleague.data.local.mappers.toTeam
import com.adammcneilly.pocketleague.data.local.utils.toListFlow
import com.adammcneilly.pocketleague.data.local.utils.toSingleFlow
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventParticipants
import com.adammcneilly.pocketleague.data.octanegg.models.toEvent
import com.adammcneilly.pocketleague.data.octanegg.models.toTeam
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import com.adammcneilly.pocketleague.data.remote.RemoteParams
import com.adammcneilly.pocketleague.sqldelight.LocalEvent
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import kotlinx.datetime.Clock

/**
 * An implementation of [EventService] that only returns data from the supplied [database]
 * but uses the given [apiClient] to [sync] data.
 */
@Suppress("TooManyFunctions")
class OfflineFirstEventService(
    private val database: PocketLeagueDB,
    private val apiClient: BaseKTORClient,
) : EventService {

    private suspend fun fetchEvents(request: EventListRequest): DataState<List<Event>> {
        return apiClient.getResponse<OctaneGGEventListResponse>(
            endpoint = EVENTS_ENDPOINT,
            params = request.toOctaneParams(),
        ).map { eventListResponse ->
            eventListResponse.events?.map(OctaneGGEvent::toEvent).orEmpty()
        }
    }

    private suspend fun fetchEventParticipants(eventId: String): DataState<List<Team>> {
        val endpoint = "$EVENTS_ENDPOINT/$eventId/participants"

        return apiClient.getResponse<OctaneGGEventParticipants>(
            endpoint = endpoint,
        ).map { octaneEventParticipants ->
            octaneEventParticipants.participants.map {
                it.toTeam()
            }
        }
    }

    override fun getEventParticipants(eventId: String): Flow<List<Team>> {
        return database
            .localEventParticipantQueries
            .selectTeamsForEvent(eventId)
            .toListFlow(LocalTeam::toTeam)
            .onStart {
                fetchAndPersistEventParticipants(eventId)
            }
    }

    private suspend fun fetchAndPersistEventParticipants(
        eventId: String
    ) {
        val apiResponse = fetchEventParticipants(eventId)

        when (apiResponse) {
            is DataState.Error -> {
                // Idk?
            }

            DataState.Loading -> {
                // Idk?
            }

            is DataState.Success -> {
                val teams = apiResponse.data

                storeTeams(teams)

                storeEventParticipants(teams, eventId)
            }
        }
    }

    private fun storeEventParticipants(
        teams: List<Team>,
        eventId: String
    ) {
        database.transaction {
            teams.forEach { team ->

                database
                    .localEventParticipantQueries
                    .insertEventParticipant(
                        eventId = eventId,
                        teamId = team.id,
                    )
            }
        }
    }

    private fun storeTeams(teams: List<Team>) {
        teams.forEach { team ->
            database
                .localTeamQueries
                .insertFullTeamObject(team.toLocalTeam())
        }
    }

    override fun getEvent(eventId: String): Flow<Event> {
        return database
            .localEventQueries
            .selectById(eventId)
            .toSingleFlow(LocalEvent::toEvent)
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
                database
                    .localEventQueries
                    .insertFullEventObject(apiResponse.data.toLocalEvent())
            }
        }
    }

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return database
            .localEventQueries
            .selectUpcoming()
            .toListFlow(LocalEvent::toEvent)
            .onStart {
                fetchAndPersistUpcomingRLCSEvents()
            }
    }

    override fun getOngoingEvents(): Flow<List<Event>> {
        return database
            .localEventQueries
            .selectOngoing()
            .toListFlow(LocalEvent::toEvent)
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
                ongoingRlcsEventsResponse
                    .data
                    .forEach { event ->
                        database
                            .localEventQueries
                            .insertFullEventObject(event.toLocalEvent())
                    }
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
                upcomingRlcsEventsResponse
                    .data
                    .forEach { event ->
                        database
                            .localEventQueries
                            .insertFullEventObject(event.toLocalEvent())
                    }
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
