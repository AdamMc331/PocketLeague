package com.adammcneilly.pocketleague.eventsummary

import com.adammcneilly.pocketleague.core.models.EventSummary

/**
 * Data layer for requesting information about event summary information.
 */
interface EventSummaryService {

    /**
     * For a given [leagueSlug], fetch all of the upcoming events
     * for that league.
     */
    suspend fun fetchUpcomingEventSummaries(
        leagueSlug: String,
    ): PLResult<List<EventSummary>>
}
