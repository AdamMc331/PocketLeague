package com.adammcneilly.pocketleague.data.match.impl

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest

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
