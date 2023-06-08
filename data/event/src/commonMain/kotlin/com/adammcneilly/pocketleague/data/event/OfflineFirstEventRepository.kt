package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

/**
 * An implementation of [EventRepository] that uses the [localEventService] as the
 * source of truth, but syncs data with our [remoteEventService].
 */
class OfflineFirstEventRepository(
    private val localEventService: LocalEventService,
    private val remoteEventService: RemoteEventService,
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

    override fun getEvent(eventId: String): Flow<Event> {
        return localEventService
            .getEvent(eventId)
            .onStart {
                val remoteResponse = remoteEventService
                    .getEventById(eventId)

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

    override fun getEventParticipants(eventId: String): Flow<List<Team>> {
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
}
