package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.DataState
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
    private val remoteEventService: RemoteEventService
) : EventRepository {
    override fun getUpcomingEvents(): Flow<List<Event>> {
        return localEventService
            .getUpcomingEvents()
            .onStart {
                val remoteResponse = remoteEventService
                    .getUpcomingEvents()

                when (remoteResponse) {
                    is DataState.Error -> {
                        println("Unable to request upcoming events: ${remoteResponse.error.message}")
                    }
                    is DataState.Success -> {
                        localEventService.insertEvents(remoteResponse.data)
                    }
                }
            }
    }

    override fun getEvent(eventId: String): Flow<Event> {
        return localEventService
            .getEvent(eventId)
            .onStart {
                val remoteResponse = remoteEventService
                    .getEvent(eventId)

                when (remoteResponse) {
                    is DataState.Error -> {
                        println("Unable to request event $eventId: ${remoteResponse.error.message}")
                    }
                    is DataState.Success -> {
                        localEventService.insertEvents(listOf(remoteResponse.data))
                    }
                }
            }
    }

    override fun getEventParticipants(eventId: String): Flow<List<Team>> {
        return localEventService
            .getEventParticipants(eventId)
            .onStart {
                val remoteResponse = remoteEventService
                    .getEventParticipants(eventId)

                when (remoteResponse) {
                    is DataState.Error -> {
                        println(
                            "Unable to request event participants for event " +
                                "$eventId: ${remoteResponse.error.message}"
                        )
                    }
                    is DataState.Success -> {
                        localEventService.insertEventParticipants(
                            teams = remoteResponse.data,
                            eventId = eventId
                        )
                    }
                }
            }
    }

    override fun getOngoingEvents(): Flow<List<Event>> {
        return localEventService
            .getOngoingEvents()
            .onStart {
                fetchAndPersistOngoingEvents()
            }
    }

    private suspend fun fetchAndPersistOngoingEvents() {
        val remoteResponse = remoteEventService
            .getOngoingEvents()

        when (remoteResponse) {
            is DataState.Error -> {
                println("Unable to request ongoing events: ${remoteResponse.error.message}")
            }

            is DataState.Success -> {
                localEventService.insertEvents(remoteResponse.data)
            }
        }
    }
}
