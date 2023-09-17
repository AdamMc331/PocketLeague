package com.adammcneilly.pocketleague.data.event.api

import com.adammcneilly.pocketleague.core.models.Event

/**
 * Defines a data contract for requesting necessary remote
 * information about events.
 */
interface RemoteEventService {

    /**
     * Fetch a list of events for the supplied [request].
     */
    suspend fun fetch(request: EventListRequest): Result<List<Event>>
}
