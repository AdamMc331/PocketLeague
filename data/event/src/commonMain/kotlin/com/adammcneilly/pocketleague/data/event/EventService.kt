package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for dealing with data within the event space.
 */
interface EventService {
    /**
     * Retrieves all of the teams participating in the given event.
     */
    fun getEventParticipants(
        eventId: String,
    ): Flow<List<Team>>

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
