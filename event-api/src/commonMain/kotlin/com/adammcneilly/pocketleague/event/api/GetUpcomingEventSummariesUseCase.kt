package com.adammcneilly.pocketleague.event.api

import com.adammcneilly.pocketleague.core.models.EventSummary
import kotlinx.coroutines.flow.Flow

/**
 * Use case to request a list of upcoming event summaries
 */
interface GetUpcomingEventSummariesUseCase {

    /**
     * @see [GetUpcomingEventSummariesUseCase]
     */
    fun invoke(leagueSlug: String): Flow<Result>

    /**
     * All possible response variations for the [GetUpcomingEventSummariesUseCase].
     */
    sealed class Result {
        /**
         * This is returned when our use case is successful and we have a list of [events] to
         * provide.
         */
        data class Success(val events: List<EventSummary>) : Result()

        /**
         * This is returned when our use case is unsuccessful and some [errorMessage] occurred.
         */
        data class Error(val errorMessage: String?) : Result()
    }
}
