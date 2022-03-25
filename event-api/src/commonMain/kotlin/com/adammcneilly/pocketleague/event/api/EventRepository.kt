package com.adammcneilly.pocketleague.event.api

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.shared.core.models.EventOverview
import com.adammcneilly.pocketleague.shared.core.models.EventSummary
import kotlinx.coroutines.flow.Flow

/**
 * Data layer definition for requesting event information.
 */
interface EventRepository {
    /**
     * Given a [leagueSlug], fetch the [EventSummary] entities that match the supplied [requestBody].
     */
    fun fetchEventSummaries(
        leagueSlug: String,
        requestBody: EventListRequestBody,
    ): Flow<Result<List<EventSummary>>>

    /**
     * Given an [eventId], fetch the [EventOverview] entity for this event.
     */
    fun fetchEventOverview(
        eventId: String,
    ): Flow<Result<EventOverview>>
}
