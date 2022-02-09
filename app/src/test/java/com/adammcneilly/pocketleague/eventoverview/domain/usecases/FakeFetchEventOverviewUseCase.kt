package com.adammcneilly.pocketleague.eventoverview.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventOverview

class FakeFetchEventOverviewUseCase : FetchEventOverviewUseCase {

    private val mockEventResults: MutableMap<String, Result<EventOverview>> = mutableMapOf()

    fun mockResultForEvent(
        eventId: String,
        result: Result<EventOverview>,
    ) {
        mockEventResults[eventId] = result
    }

    override suspend fun invoke(eventId: String): Result<EventOverview> {
        return mockEventResults[eventId]!!
    }
}
