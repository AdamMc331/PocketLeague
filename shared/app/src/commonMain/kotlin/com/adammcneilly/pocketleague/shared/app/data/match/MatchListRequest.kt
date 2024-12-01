package com.adammcneilly.pocketleague.shared.app.data.match

/**
 * Defines an enumeration of the ways we can request a list of matches
 * in the PocketLeague app.
 */
sealed interface MatchListRequest {
    /**
     * Requests matches (should only be one) with the given [matchId].
     */
    data class Id(
        val matchId: String,
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
        val eventId: String,
        val stageId: String,
    ) : MatchListRequest
}
