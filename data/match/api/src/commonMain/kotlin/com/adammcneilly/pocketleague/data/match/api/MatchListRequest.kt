package com.adammcneilly.pocketleague.data.match.api

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match

/**
 * Defines an enumeration of the ways we can request a list of matches
 * in the PocketLeague app.
 */
sealed interface MatchListRequest {

    /**
     * Requests matches (should only be one) with the given [matchId].
     */
    data class Id(
        val matchId: Match.Id,
    ) : MatchListRequest

    /**
     * Request a list of matches within a given date range.
     */
    data class DateRange(
        val startDateUTC: String,
        val endDateUTC: String,
    ) : MatchListRequest

    /**
     * Finds a list of matches with a given [eventId] and [stageId] combination.
     */
    data class EventStage(
        val eventId: Event.Id,
        val stageId: com.adammcneilly.pocketleague.core.models.EventStage.Id,
    ) : MatchListRequest
}
