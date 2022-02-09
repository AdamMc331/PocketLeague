package com.adammcneilly.pocketleague.eventoverview.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.event.data.EventService
import com.adammcneilly.pocketleague.models.EventOverview
import javax.inject.Inject

/**
 * A concrete implementation of a [FetchEventOverviewUseCase] that requests information from the
 * supplied [service].
 */
class FetchEventOverviewUseCaseImpl @Inject constructor(
    private val service: EventService,
) : FetchEventOverviewUseCase {

    override suspend fun invoke(eventId: String): Result<EventOverview> {
        return service.fetchEventOverview(eventId = eventId)
    }
}
