package com.adammcneilly.pocketleague.eventsummary.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.models.EventSummary

class FakeFetchUpcomingEventsUseCase : FetchUpcomingEventsUseCase {
    lateinit var mockResult: Result<List<EventSummary>>

    override suspend fun invoke(): Result<List<EventSummary>> {
        return mockResult
    }
}
