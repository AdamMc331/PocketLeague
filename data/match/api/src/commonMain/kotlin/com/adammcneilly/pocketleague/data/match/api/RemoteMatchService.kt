package com.adammcneilly.pocketleague.data.match.api

import com.adammcneilly.pocketleague.core.models.Match

/**
 * Defines a data contract for requesting necessary remote
 * information about matches.
 */
interface RemoteMatchService {

    /**
     * Fetch a list of matches for the supplied [request].
     */
    suspend fun fetch(request: MatchListRequest): Result<List<Match>>
}
