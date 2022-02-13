package com.adammcneilly.pocketleague.event.data

import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.eventsummary.EventSummaryService
import com.adammcneilly.pocketleague.eventsummary.PLResult

class FakeEventSummaryService : EventSummaryService {
    private val upcomingEventsForLeagueResponses: MutableMap<String, PLResult<List<EventSummary>>> = mutableMapOf()

    fun mockUpcomingEventsForLeague(
        leagueSlug: String,
        upcomingEvents: PLResult<List<EventSummary>>,
    ) {
        upcomingEventsForLeagueResponses[leagueSlug] = upcomingEvents
    }

    override suspend fun fetchUpcomingEventSummaries(leagueSlug: String): PLResult<List<EventSummary>> {
        return upcomingEventsForLeagueResponses[leagueSlug]!!
    }
}
