package com.adammcneilly.pocketleague.eventsummary.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventSummary

/**
 * Requests a list of upcoming RLCS events.
 */
interface FetchUpcomingEventsUseCase {

    /**
     * @see [FetchUpcomingEventsUseCase]
     */
    suspend operator fun invoke(): Result<List<com.adammcneilly.pocketleague.core.models.EventSummary>>
}
