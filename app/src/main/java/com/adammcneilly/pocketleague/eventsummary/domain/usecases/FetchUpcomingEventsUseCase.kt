package com.adammcneilly.pocketleague.eventsummary.domain.usecases

import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.core.models.PLResult

/**
 * Requests a list of upcoming RLCS events.
 */
interface FetchUpcomingEventsUseCase {

    /**
     * @see [FetchUpcomingEventsUseCase]
     */
    suspend operator fun invoke(): PLResult<List<EventSummary>>
}
