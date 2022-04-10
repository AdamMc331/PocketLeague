package com.adammcneilly.pocketleague.event.api

import com.adammcneilly.pocketleague.core.models.EventOverview
import com.adammcneilly.pocketleague.core.models.EventSummary
import kotlinx.coroutines.flow.Flow

/**
 * Data layer definition for requesting event information.
 */
interface EventRepository {
    /**
     * Fetch a list of [EventSummary] entities. Actual querying support will come later.
     */
    fun fetchEventSummaries(): Flow<Result<List<EventSummary>>>

    /**
     * Given an [eventId], fetch the [EventOverview] entity for this event.
     */
    fun fetchEventOverview(
        eventId: String,
    ): Flow<Result<EventOverview>>
}
