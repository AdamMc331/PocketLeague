package com.adammcneilly.pocketleague.data.match.api

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import kotlinx.coroutines.flow.Flow

/**
 * Represents a local source of truth for the match domain.
 */
interface LocalMatchService {

    /**
     * Inserts the supplied [matches] into our source of truth.
     */
    suspend fun insertMatches(matches: List<Match>)

    /**
     * Stream a list of matches for the supplied [request].
     */
    fun stream(request: MatchListRequest): Flow<List<Match>>
}
