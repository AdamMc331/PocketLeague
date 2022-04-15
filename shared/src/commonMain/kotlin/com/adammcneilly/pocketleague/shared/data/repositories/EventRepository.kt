package com.adammcneilly.pocketleague.shared.data.repositories

import com.adammcneilly.pocketleague.shared.data.DataResult
import com.adammcneilly.pocketleague.shared.models.Event
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for dealing with data within the event space.
 */
interface EventRepository {
    /**
     * Returns a stream of [Event] entities inside of a list. In the future, we'll
     * modify this to consume a request & intend to return events that meet certain
     * conditions.
     */
    fun fetchEvents(): Flow<DataResult<List<Event>>>
}
