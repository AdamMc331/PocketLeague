package com.adammcneilly.pocketleague.eventsummary.domain.usecases

import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.core.models.PLResult

class FakeFetchUpcomingEventsUseCase : FetchUpcomingEventsUseCase {
    lateinit var mockResult: PLResult<List<EventSummary>>

    override suspend fun invoke(): PLResult<List<EventSummary>> {
        return mockResult
    }
}
