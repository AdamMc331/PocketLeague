package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Match
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data layer for any match related requests.
 */
interface MatchRepository {

    /**
     * Provide a flow response of matches for the given [request].
     *
     * All data is provided from our source of truth, and refreshed unless
     * [refreshCache] is set to false. This is ideal for stale data that is unlikely to
     * have changed.
     */
    fun stream(
        request: MatchListRequest,
        refreshCache: Boolean = true,
    ): Flow<List<Match>>
}
