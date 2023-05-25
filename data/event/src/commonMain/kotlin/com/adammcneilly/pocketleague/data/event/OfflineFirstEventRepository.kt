package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.remote.graphql.TournamentDetailQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.datetime.Instant

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
        // For testing sake, we're shortcutting all of this and returning an event
        // from our apollo client just to make sure that it can work.
        return flow {
            val apolloResponse = startGGApolloClient
                .query(TournamentDetailQuery(slug = "rlcs-2022-23-spring-cup-north-america"))
                .execute()

            val apolloTournament = apolloResponse.data?.tournament

            if (apolloTournament != null) {
                val domainEvent = with(apolloTournament) {
                    val startUtc = (apolloTournament.startAt as? Long)?.let { startAt ->
                        Instant.fromEpochMilliseconds(startAt).toString()
                    }

                    val endUtc = (apolloTournament.endAt as? Long)?.let { endAt ->
                        Instant.fromEpochMilliseconds(endAt).toString()
                    }

                    Event(
                        id = this.id.orEmpty(),
                        name = this.name.orEmpty(),
                        startDateUTC = startUtc,
                        endDateUTC = endUtc,
                        imageURL = this.images?.firstOrNull()?.url,
                        stages = emptyList(),
                        tier = EventTier.Unknown,
                        mode = "",
                        region = EventRegion.Unknown,
                        lan = this.hasOfflineEvents == true,
                        prize = null,
                    )
                }

                emit(domainEvent)
            } else {
                // Need to handle API error here.
            }
        }

//        return localEventService
//            .getEvent(eventId)
//            .onStart {
//                val remoteResponse = remoteEventService
//                    .getEvent(eventId)
//
//                when (remoteResponse) {
//                    is DataState.Error -> {
//                        println("Unable to request event $eventId: ${remoteResponse.error.message}")
//                    }
//                    is DataState.Success -> {
//                        localEventService.insertEvents(listOf(remoteResponse.data))
//                    }
//                }
//            }
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
                                "$eventId: ${remoteResponse.error.message}",
                        )
                    }
                    is DataState.Success -> {
                        localEventService.insertEventParticipants(
                            teams = remoteResponse.data,
                            eventId = eventId,
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
