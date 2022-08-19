package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team

/**
 * Defines the data contract for dealing with data within the event space.
 */
interface EventRepository {
    /**
     * Returns a stream of [Event] entities as a list. We should only return events that meet
     * the criteria defined by the given [request].
     */
    suspend fun fetchEvents(
        request: EventListRequest,
    ): DataState<List<Event>>

    /**
     * Retrieves a single [Event] entity for the given [eventId].
     */
    suspend fun fetchEvent(
        eventId: String,
    ): DataState<Event>

    /**
     * Fetches every [Team] participating in an event with the given [eventId].
     */
    suspend fun fetchEventParticipants(
        eventId: String,
    ): DataState<List<Team>>
}
