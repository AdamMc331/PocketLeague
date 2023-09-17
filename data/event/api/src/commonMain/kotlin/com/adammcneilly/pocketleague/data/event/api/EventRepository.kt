package com.adammcneilly.pocketleague.data.event.api

import com.adammcneilly.pocketleague.core.models.Event
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data layer for any event related requests.
 */
interface EventRepository {

    /**
     * Provide a flow response of events for the given [request].
     *
     * All data is provided from our source of truth, and refreshed unless
     * [refreshCache] is set to false. This is ideal for stale data that is unlikely to
     * have changed.
     */
    fun stream(
        request: EventListRequest,
        refreshCache: Boolean = true,
    ): Flow<List<Event>>
}
