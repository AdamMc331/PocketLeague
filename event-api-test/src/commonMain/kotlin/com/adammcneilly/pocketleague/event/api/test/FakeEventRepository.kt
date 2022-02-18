package com.adammcneilly.pocketleague.event.api.test

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.event.api.EventRepository
import kotlinx.coroutines.flow.Flow

/**
 * Concrete implementation of [EventRepository] that provides mock responses
 * to be used in unit tests.
 */
class FakeEventRepository : EventRepository {
    val upcomingEventSummariesByLeagues: MutableMap<String, Flow<Result<List<EventSummary>>>> = mutableMapOf()

    override fun fetchUpcomingEventSummaries(leagueSlug: String): Flow<Result<List<EventSummary>>> {
        return upcomingEventSummariesByLeagues[leagueSlug]!!
    }
}