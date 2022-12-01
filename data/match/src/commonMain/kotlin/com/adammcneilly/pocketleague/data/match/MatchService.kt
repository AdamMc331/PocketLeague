package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for dealing with data within the match space.
 */
interface MatchService {

    /**
     * Fetches detailed information about a [Match] using the supplied [matchId].
     */
    suspend fun fetchMatchDetail(
        matchId: String,
    ): DataState<Match>

    /**
     * Returns a reactive stream of [Match] entities that have occured within the last week.
     */
    fun getPastWeeksMatches(): Flow<List<Match>>

    /**
     * Retrieves all matches that occured in the given [eventId] and [stageId].
     */
    fun getMatchesForEventStage(
        eventId: String,
        stageId: String,
    ): Flow<List<Match>>
}
