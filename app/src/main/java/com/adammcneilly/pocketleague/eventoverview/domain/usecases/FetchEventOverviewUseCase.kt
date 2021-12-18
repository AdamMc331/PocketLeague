package com.adammcneilly.pocketleague.eventoverview.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.eventoverview.domain.models.EventOverview

/**
 * Consumes a slug identifier for an event, and fetches the [EventOverview] information.
 */
interface FetchEventOverviewUseCase {
    /**
     * @see [FetchEventOverviewUseCase]
     */
    suspend operator fun invoke(eventId: String): Result<EventOverview>
}
