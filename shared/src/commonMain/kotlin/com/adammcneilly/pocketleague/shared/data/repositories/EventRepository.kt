package com.adammcneilly.pocketleague.shared.data.repositories

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.data.models.EventListRequest
import com.adammcneilly.pocketleague.shared.models.Event
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for dealing with data within the event space.
 */
interface EventRepository {
    /**
     * Returns a stream of [Event] entities as a list. We should only return events that meet
     * the criteria defined by the given [request].
     */
    fun fetchEvents(
        request: EventListRequest,
    ): Flow<DataState<List<Event>>>
}
