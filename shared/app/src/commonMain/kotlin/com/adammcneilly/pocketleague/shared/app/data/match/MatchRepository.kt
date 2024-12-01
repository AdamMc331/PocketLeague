package com.adammcneilly.pocketleague.shared.app.data.match

import com.adammcneilly.pocketleague.shared.app.core.models.Match
import kotlinx.coroutines.flow.Flow

/**
 * Defines the data layer for any match related requests.
 */
interface MatchRepository {
    fun getMatches(
        request: MatchListRequest,
    ): Flow<List<Match>>
}
