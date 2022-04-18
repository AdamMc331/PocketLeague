package com.adammcneilly.pocketleague.shared.matchlist

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
    operator fun invoke(numDays: Int): Flow<Result>

    /**
     * Defines all possible return types for a [GetRecentMatchesUseCase].
     */
    sealed class Result {
        /**
         * This will be returned when we've successfully retrieved a list of [matches].
         */
        data class Success(
            val matches: List<Match>,
        ) : Result()

        /**
         * This will be returned if some [error] occurs when requesting a list of matches.
         */
        data class Error(
            val error: Throwable,
        ) : Result()
    }
}
