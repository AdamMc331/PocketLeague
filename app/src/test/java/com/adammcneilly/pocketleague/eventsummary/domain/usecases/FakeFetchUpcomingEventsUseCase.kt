package com.adammcneilly.pocketleague.eventsummary.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventSummary

class FakeFetchUpcomingEventsUseCase : FetchUpcomingEventsUseCase {
    lateinit var mockResult: Result<List<com.adammcneilly.pocketleague.core.models.EventSummary>>

    override suspend fun invoke(): Result<List<com.adammcneilly.pocketleague.core.models.EventSummary>> {
        return mockResult
    }
}
