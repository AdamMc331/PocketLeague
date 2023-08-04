package com.adammcneilly.pocketleague.data.match.api

import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow

/**
 * Data contract for requesting match information from a local data source.
 */
interface LocalMatchService {

    /**
     * Fetches detailed information about a [Match] using the supplied [matchId].
     */
    fun observeMatch(
        matchId: Match.Id,
    ): Flow<Match>

    /**
     * Returns a reactive stream of [Match] entities that have occured within the last week.
     */
    fun observeMatchesInDateRange(
        startDateUTC: String,
        endDateUTC: String,
    ): Flow<List<Match>>

    /**
     * Insert the supplied [matches] to our local data source.
     */
    suspend fun insertMatches(
        matches: List<Match>,
    )
}
