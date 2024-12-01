package com.adammcneilly.pocketleague.shared.app.data.event

import com.adammcneilly.pocketleague.shared.app.core.models.Event
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data layer for any [Event] related requests.
 */
interface EventRepository {
    /**
     * Stream a list of [Event] entities based on the supplied [request].
     */
    fun getEvents(
        request: EventListRequest,
    ): Flow<List<Event>>
}
