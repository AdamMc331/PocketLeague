package com.adammcneilly.pocketleague.shared.eventsummarylist.domain

import com.adammcneilly.pocketleague.shared.core.models.EventSummary
import kotlinx.coroutines.flow.Flow

/**
 * Use case to request a list of event summaries.
 */
interface GetEventSummariesUseCase {

    /**
     * @see [GetEventSummariesUseCase]
     */
    fun invoke(
        leagueSlug: String,
        request: Request,
    ): Flow<Result>

    /**
     * Data class to define all of the request parameters for fetching a list of event summaries.
     *
     * @property[upcoming] True if we want to request the upcoming events, false otherwise.
     */
    data class Request(
        val upcoming: Boolean,
    )

    /**
     * All possible response variations for the [GetEventSummariesUseCase].
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
