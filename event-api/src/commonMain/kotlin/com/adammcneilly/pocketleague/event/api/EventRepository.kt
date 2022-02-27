package com.adammcneilly.pocketleague.event.api

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventSummary
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
}
