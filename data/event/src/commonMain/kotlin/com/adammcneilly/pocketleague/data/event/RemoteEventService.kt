package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team

/**
 * Defines the data contract for requesting data from a remote service for event information.
 */
interface RemoteEventService {

    /**
     * Retrieves a list of upcoming [Event] entities for events that haven't started yet.
     */
    suspend fun getUpcomingEvents(): Result<List<Event>>

    /**
     * Retrieves an [Event] by it's unique [eventId].
     */
    suspend fun getEvent(eventId: Event.Id): Result<Event>

    /**
     * Observe the list of [Team] entities that participated in the given [eventId].
     */
    suspend fun getEventParticipants(eventId: Event.Id): Result<List<Team>>

    /**
     * Observe a list of [Event] entities that are happening now.
     */
    suspend fun getOngoingEvents(): Result<List<Event>>
}
