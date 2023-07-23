package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.SwissStageTeamResult
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.match.LocalMatchService
import com.adammcneilly.pocketleague.data.match.RemoteMatchService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

/**
 * An implementation of [EventRepository] that uses the [localEventService] as the
 * source of truth, but syncs data with our [remoteEventService].
 */
class OfflineFirstEventRepository(
    private val localEventService: LocalEventService,
    private val remoteEventService: RemoteEventService,
    private val remoteMatchService: RemoteMatchService,
    private val localMatchService: LocalMatchService,
) : EventRepository {
    override fun getUpcomingEvents(): Flow<List<Event>> {
        return localEventService
            .getUpcomingEvents()
            .onStart {
                val remoteResponse = remoteEventService
                    .getUpcomingEvents()

                remoteResponse.fold(
                    onSuccess = { events ->
                        localEventService.insertEvents(events)
                    },
                    onFailure = { error ->
                        println("Unable to request upcoming events: ${error.message}")
                    },
                )
            }
    }

    override fun getEvent(eventId: Event.Id): Flow<Event> {
        return localEventService
            .getEvent(eventId)
            .onStart {
                val remoteResponse = remoteEventService
                    .getEvent(eventId)

                remoteResponse.fold(
                    onSuccess = { event ->
                        localEventService.insertEvents(listOf(event))
                    },
                    onFailure = { error ->
                        println("Unable to request event $eventId: ${error.message}")
                    },
                )
            }
    }

    override fun getEventParticipants(eventId: Event.Id): Flow<List<Team>> {
        return localEventService
            .getEventParticipants(eventId)
            .onStart {
                val remoteResponse = remoteEventService
                    .getEventParticipants(eventId)

                remoteResponse.fold(
                    onSuccess = { participants ->
                        localEventService.insertEventParticipants(
                            teams = participants,
                            eventId = eventId,
                        )
                    },
                    onFailure = { error ->
                        println(
                            "Unable to request event participants for event " +
                                "$eventId: ${error.message}",
                        )
                    },
                )
            }
    }

    override fun getOngoingEvents(): Flow<List<Event>> {
        return localEventService
            .getOngoingEvents()
            .onStart {
                val remoteResponse = remoteEventService
                    .getOngoingEvents()

                remoteResponse.fold(
                    onSuccess = { events ->
                        localEventService.insertEvents(events)
                    },
                    onFailure = { error ->
                        println("Unable to request ongoing events: ${error.message}")
                    },
                )
            }
    }

    override fun getSwissStageResults(eventId: String, stageId: String): Flow<List<SwissStageTeamResult>> {
        return localEventService
            .getSwissStageResults(eventId, stageId)
            .onStart {
                val remoteResponse = remoteMatchService
                    .getMatchesForEventStage(eventId, stageId)

                remoteResponse.fold(
                    onSuccess = { matches ->
                        localMatchService.insertMatches(matches)
                    },
                    onFailure = { error ->
                        println("Unable to request event stage matches: ${error.message}")
                    },
                )
            }
    }
}
