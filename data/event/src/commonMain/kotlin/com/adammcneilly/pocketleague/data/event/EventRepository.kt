package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for dealing with data within the event space.
 */
interface EventRepository {
    /**
     * Retrieves a list of upcoming [Event] entities for events that haven't started yet.
     */
    fun getUpcomingEvents(): Flow<List<Event>>

    /**
     * Retrieves an [Event] by it's unique [eventId].
     */
    fun getEvent(eventId: Event.Id): Flow<Event>

    /**
     * Observe the list of [Team] entities that participated in the given [eventId].
     */
    fun getEventParticipants(eventId: Event.Id): Flow<List<Team>>

    /**
     * Observe a list of [Event] entities that are happening now.
     */
    fun getOngoingEvents(): Flow<List<Event>>
}
