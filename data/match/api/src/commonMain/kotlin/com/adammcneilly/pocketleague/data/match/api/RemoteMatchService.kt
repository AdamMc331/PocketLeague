package com.adammcneilly.pocketleague.data.match.api

import com.adammcneilly.pocketleague.core.models.Match

/**
 * Data contract for requesting match information from a local data source.
 */
interface RemoteMatchService {

    /**
     * Fetches detailed information about a [Match] using the supplied [matchId].
     */
    suspend fun fetchMatch(
        matchId: Match.Id,
    ): Result<Match>

    /**
     * Returns a reactive stream of [Match] entities that have occured within the last week.
     */
    suspend fun fetchMatchesInDateRange(
        startDateUTC: String,
        endDateUTC: String,
    ): Result<List<Match>>
}
