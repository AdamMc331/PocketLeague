package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.local.PocketLeagueDB
import com.adammcneilly.pocketleague.data.local.mappers.parseEvent
import com.adammcneilly.pocketleague.data.local.mappers.parseStage
import com.adammcneilly.pocketleague.data.local.mappers.toEvent
import com.adammcneilly.pocketleague.data.local.mappers.toLocalEvent
import com.adammcneilly.pocketleague.data.local.mappers.toLocalEventStage
import com.adammcneilly.pocketleague.data.local.mappers.toLocalTeam
import com.adammcneilly.pocketleague.data.local.mappers.toTeam
import com.adammcneilly.pocketleague.data.local.util.asFlowList
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEvent
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGEventParticipants
import com.adammcneilly.pocketleague.data.octanegg.models.toEvent
import com.adammcneilly.pocketleague.data.octanegg.models.toTeam
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import com.adammcneilly.pocketleague.data.remote.RemoteParams
import com.adammcneilly.pocketleague.sqldelight.EventWithStages
import com.adammcneilly.pocketleague.sqldelight.LocalEvent
import com.adammcneilly.pocketleague.sqldelight.LocalEventParticipant
import com.adammcneilly.pocketleague.sqldelight.LocalTeam
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.datetime.Clock

/**
 * An implementation of [EventService] that only returns data from the supplied [database]
 * but uses the given [apiClient] to [sync] data.
 */
class OfflineFirstEventService(
    private val database: PocketLeagueDB,
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
            .localEventQueries
            .selectById(eventId)
            .asFlow()
            .mapToList()
            .map { eventWithStageList ->
                eventWithStageList
                    .groupBy(EventWithStages::parseEvent)
                    .map {
                        val stages = it.value.map(EventWithStages::parseStage)
                        it.key.copy(stages = stages)
                    }
                    .first()
            }
            .onStart {
                fetchAndPersistEvent(eventId)
            }
    }

    override fun getEventParticipants(eventId: String): Flow<List<Team>> {
        return database
            .localEventParticipantQueries
            .selectParticipantsForEvent(eventId)
            .asFlowList(LocalTeam::toTeam)
            .onStart {
                fetchAndPersistParticipants(eventId)
            }
    }

    private suspend fun fetchAndPersistParticipants(
        eventId: String,
    ) {
        val apiResponse = fetchEventParticipants(eventId)

        // We should log if an error occurs here.
        val teams = (apiResponse as? DataState.Success)?.data.orEmpty()

        teams.forEach { team ->
            database
                .localTeamQueries
                .insertFullTeamObject(team.toLocalTeam())

            // This assumes the event entity is already in DB.
            // Test this?
            database
                .localEventParticipantQueries
                .insertEventParticipant(
                    LocalEventParticipant(
                        eventId = eventId,
                        teamId = team.id,
                    )
                )
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

                apiResponse.data.stages.map { stage ->
                    database
                        .localEventStageQueries
                        .insertFullEventStage(stage.toLocalEventStage(apiResponse.data.id))
                }
            }
        }
    }

    override fun getUpcomingEvents(): Flow<List<Event>> {
        return database
            .localEventQueries
            .selectUpcoming()
            .asFlowList(LocalEvent::toEvent)
    }

    override suspend fun sync() {
        fetchAndPersistingUpcomingRLCSEvents()
    }

    private suspend fun fetchAndPersistingUpcomingRLCSEvents() {
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
                upcomingRlcsEventsResponse.data.forEach { event ->
                    database
                        .localEventQueries
                        .insertFullEventObject(event.toLocalEvent())
                }
            }
        }
    }

    companion object {
        const val EVENTS_ENDPOINT = "/events"
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
