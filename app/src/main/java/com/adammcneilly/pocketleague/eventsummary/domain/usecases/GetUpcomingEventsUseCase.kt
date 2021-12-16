package com.adammcneilly.pocketleague.eventsummary.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary

/**
 * Requests a list of upcoming RLCS events.
 */
interface GetUpcomingEventsUseCase {

    /**
     * @see [GetUpcomingEventsUseCase]
     */
    suspend operator fun invoke(): Result<List<EventSummary>>
}
