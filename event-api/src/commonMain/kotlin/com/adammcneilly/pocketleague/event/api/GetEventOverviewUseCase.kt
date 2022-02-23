package com.adammcneilly.pocketleague.event.api

import com.adammcneilly.pocketleague.core.models.EventOverview
import kotlinx.coroutines.flow.Flow

/**
 * Fetches [EventOverview] information for a specific event ID.
 */
interface GetEventOverviewUseCase {

    /**
     * @see [GetEventOverviewUseCase]
     */
    fun invoke(eventId: String): Flow<Result>

    /**
     * All possible response variations for the [GetEventOverviewUseCase].
     */
    sealed class Result {
        /**
         * This is returned when our use case is successful and we have an [eventOverview] to
         * provide.
         */
        data class Success(val eventOverview: EventOverview) : Result()

        /**
         * This is returned when our use case is unsuccessful and some [errorMessage] occurred.
         */
        data class Error(val errorMessage: String?) : Result()
    }
}
