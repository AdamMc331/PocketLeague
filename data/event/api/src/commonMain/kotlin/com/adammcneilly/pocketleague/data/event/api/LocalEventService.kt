package com.adammcneilly.pocketleague.data.event.api

import com.adammcneilly.pocketleague.core.models.Event
import kotlinx.coroutines.flow.Flow

/**
 * Represents a local source of truth for the event domain.
 */
interface LocalEventService {

    /**
     * Inserts the supplied [events] into our source of truth.
     */
    suspend fun insertEvents(events: List<Event>)

    /**
     * Stream a list of events for the supplied [request].
     */
    fun stream(request: EventListRequest): Flow<List<Event>>
}
