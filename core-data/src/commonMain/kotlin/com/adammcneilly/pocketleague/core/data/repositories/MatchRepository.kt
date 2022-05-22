package com.adammcneilly.pocketleague.core.data.repositories

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.data.models.MatchListRequest
import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data contract for dealing with data within the match space.
 */
interface MatchRepository {

    /**
     * Returns a stream of [Match] entities as a list. We should only return
     * events that meet the criteria defined by the given [request].
     */
    fun fetchMatches(
        request: MatchListRequest,
    ): Flow<DataState<List<Match>>>
}
