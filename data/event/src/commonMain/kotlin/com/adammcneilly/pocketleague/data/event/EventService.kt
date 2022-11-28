package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for dealing with data within the event space.
 */
interface EventService {
    /**
     * Returns a stream of [Event] entities as a list. We should only return events that meet
     * the criteria defined by the given [request].
     */
    @Deprecated("We'll replace this legacy version with a clearer function.")
    suspend fun fetchEvents(
        request: EventListRequest,
    ): DataState<List<Event>>

    /**
     * Retrieves a single [Event] entity for the given [eventId].
     */
    @Deprecated("We'll replace this with a flowable version.")
    suspend fun fetchEvent(
        eventId: String,
    ): DataState<Event>

    /**
     * Fetches every [Team] participating in an event with the given [eventId].
     */
    @Deprecated("Replace with flowable version")
    suspend fun fetchEventParticipants(
        eventId: String,
    ): DataState<List<Team>>

    /**
     * Retrieves a list of upcoming [Event] entities for events that haven't started yet.
     */
    fun getUpcomingEvents(): Flow<List<Event>>

    /**
     * Retrieves an [Event] by it's unique [eventId].
     */
    fun getEvent(eventId: String): Flow<Event>

    /**
     * Retrieve a list of rlcs events happening now.
     */
    fun getOngoingEvents(): Flow<List<Event>>
}
