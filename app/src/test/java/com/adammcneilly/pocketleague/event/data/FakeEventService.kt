package com.adammcneilly.pocketleague.event.data

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.eventoverview.domain.models.EventOverview
import com.adammcneilly.pocketleague.swiss.domain.models.SwissStage

class FakeEventService : EventService {
    private val upcomingEventsForLeagueResponses: MutableMap<String, Result<List<EventSummary>>> = mutableMapOf()
    private val eventOverviewResponses: MutableMap<String, Result<EventOverview>> = mutableMapOf()

    fun mockUpcomingEventsForLeague(
        leagueSlug: String,
        upcomingEvents: Result<List<EventSummary>>,
    ) {
        upcomingEventsForLeagueResponses[leagueSlug] = upcomingEvents
    }

    fun mockEventOverviewResponse(
        eventId: String,
        overview: Result<EventOverview>,
    ) {
        eventOverviewResponses[eventId] = overview
    }

    override suspend fun fetchSwissStage(eventName: String): Result<SwissStage> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchUpcomingEvents(leagueSlug: String): Result<List<EventSummary>> {
        return upcomingEventsForLeagueResponses[leagueSlug]!!
    }

    override suspend fun fetchEventOverview(eventId: String): Result<EventOverview> {
        return eventOverviewResponses[eventId]!!
    }
}
