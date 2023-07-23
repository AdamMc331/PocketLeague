package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.Match

/**
 * Data contract for requesting match information from a local data source.
 */
interface RemoteMatchService {

    /**
     * Fetches detailed information about a [Match] using the supplied [matchId].
     */
    suspend fun getMatchDetail(
        matchId: Match.Id,
    ): Result<Match>

    /**
     * Returns a reactive stream of [Match] entities that have occured within the last week.
     */
    suspend fun getMatchesInDateRange(
        startDateUTC: String,
        endDateUTC: String,
    ): Result<List<Match>>

    /**
     * Retrieve a list of match entities that haven't happened yet.
     */
    suspend fun getUpcomingMatches(): Result<List<Match>>

    /**
     * Retrieves all matches that occurred in the given [eventId] and [stageId].
     */
    suspend fun getMatchesForEventStage(
        eventId: Event.Id,
        stageId: EventStage.Id,
    ): Result<List<Match>>
}
