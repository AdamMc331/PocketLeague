package com.adammcneilly.pocketleague.data.event

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.SwissStageTeamResult
import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for requesting local information from the events domain.
 */
interface LocalEventService {
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

    /**
     * Persist the supplied [events] in a local data source.
     */
    suspend fun insertEvents(events: List<Event>)

    /**
     * Persist the supplied [teams] in a local data source.
     */
    suspend fun insertEventParticipants(
        teams: List<Team>,
        eventId: Event.Id,
    )

    /**
     * Fetch the results by team for a given [eventId] and [stageId].,
     */
    fun getSwissStageResults(
        eventId: String,
        stageId: String,
    ): Flow<List<SwissStageTeamResult>>
}
