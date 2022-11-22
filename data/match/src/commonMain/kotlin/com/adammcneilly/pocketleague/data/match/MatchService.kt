package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.models.Match

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
    suspend fun fetchMatches(
        request: MatchListRequest,
    ): DataState<List<Match>>
}
