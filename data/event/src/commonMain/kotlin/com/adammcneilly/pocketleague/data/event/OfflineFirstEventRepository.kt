package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

/**
 * An implementation of [EventRepository] that uses the [localDataSource] as the
 * source of truth, but syncs data with our [remoteDataSource].
 */
class OfflineFirstEventRepository(
    private val localDataSource: EventRepository,
    private val remoteDataSource: EventRepository,
) : EventRepository {
    override fun getUpcomingEvents(): Flow<List<Event>> {
        return localDataSource
            .getUpcomingEvents()
            .onStart {
                remoteDataSource
                    .getUpcomingEvents()
                    .collect { events ->
                        localDataSource.insertEvents(events)
                    }
            }
    }

    override fun getEvent(eventId: String): Flow<Event> {
        return localDataSource
            .getEvent(eventId)
            .onStart {
                remoteDataSource
                    .getEvent(eventId)
                    .collect { event ->
                        localDataSource.insertEvents(listOf(event))
                    }
            }
    }

    override fun getEventParticipants(eventId: String): Flow<List<Team>> {
        return localDataSource
            .getEventParticipants(eventId)
            .onStart {
                remoteDataSource
                    .getEventParticipants(eventId)
                    .collect { teams ->
                        localDataSource.insertEventParticipants(teams, eventId)
                    }
            }
    }

    override fun getOngoingEvents(): Flow<List<Event>> {
        return localDataSource
            .getOngoingEvents()
            .onEach {
                println("ARM - local ongoing: $it")
            }
            .onStart {
                remoteDataSource
                    .getOngoingEvents()
                    .collect { events ->
                        println("ARM - remote ongoing: $events")

                        localDataSource.insertEvents(events)
                    }
            }
    }

    override suspend fun insertEvents(events: List<Event>) {
        localDataSource.insertEvents(events)
    }

    override suspend fun insertEventParticipants(teams: List<Team>, eventId: String) {
        localDataSource.insertEventParticipants(teams, eventId)
    }
}
