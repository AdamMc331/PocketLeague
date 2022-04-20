package com.adammcneilly.pocketleague.shared.data.repositories

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.data.models.MatchListRequest
import com.adammcneilly.pocketleague.shared.models.Match
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
