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
     * Returns a stream of [Match] entities as a list. We should only return
     * events that meet the criteria defined by the given [request].
     */
    @Deprecated(
        "We no longer want to make one off requests, " +
            "prefer the reactive alternative."
    )
    fun fetchMatches(
        request: MatchListRequest,
    ): Flow<DataState<List<Match>>>

    /**
     * Returns a reactive stream of [Match] entities that have occured within the last week.
     */
    fun getPastWeeksMatches(): Flow<List<Match>>
}
