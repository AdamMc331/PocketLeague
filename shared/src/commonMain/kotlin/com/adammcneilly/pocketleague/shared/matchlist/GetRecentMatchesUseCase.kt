package com.adammcneilly.pocketleague.shared.matchlist

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.models.Match
import kotlinx.coroutines.flow.Flow

/**
 * A use case to fetch recent matches. The caller can customize within how many
 * days "recent" is.
 */
interface GetRecentMatchesUseCase {

    /**
     * @see [GetRecentMatchesUseCase]
     *
     * @param[numDays] The number of days back to pull recent events for.
     */
    operator fun invoke(numDays: Int): Flow<DataState<List<Match>>>
}
