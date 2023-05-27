package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Match

/**
 * Data contract for requesting match information from a local data source.
 */
interface RemoteMatchService {

    /**
     * Fetches detailed information about a [Match] using the supplied [matchId].
     */
    suspend fun getMatchDetail(
        matchId: String,
    ): Result<Match>

    /**
     * Returns a reactive stream of [Match] entities that have occured within the last week.
     */
    suspend fun getPastWeeksMatches(): Result<List<Match>>

    /**
     * Retrieve a list of match entities that haven't happened yet.
     */
    suspend fun getUpcomingMatches(): Result<List<Match>>

    /**
     * Retrieves all matches that occurred in the given [eventId] and [stageId].
     */
    suspend fun getMatchesForEventStage(
        eventId: String,
        stageId: String,
    ): Result<List<Match>>
}
