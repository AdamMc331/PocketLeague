package com.adammcneilly.pocketleague.event.api.test

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventOverview
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.api.EventListRequestBody
import com.adammcneilly.pocketleague.event.api.EventRepository
import kotlinx.coroutines.flow.Flow

/**
 * Concrete implementation of [EventRepository] that provides mock responses
 * to be used in unit tests.
 */
class FakeEventRepository : EventRepository {
    val upcomingEventSummariesByLeagues: MutableMap<String, Flow<Result<List<EventSummary>>>> = mutableMapOf()
    val eventOverviewsById: MutableMap<String, Flow<Result<EventOverview>>> = mutableMapOf()

    override fun fetchEventSummaries(
        leagueSlug: String,
        requestBody: EventListRequestBody,
    ): Flow<Result<List<EventSummary>>> {
        return upcomingEventSummariesByLeagues[leagueSlug]!!
    }

    override fun fetchEventOverview(eventId: String): Flow<Result<EventOverview>> {
        return eventOverviewsById[eventId]!!
    }
}
