package com.adammcneilly.pocketleague.event.api

import com.adammcneilly.pocketleague.core.models.EventSummary
import kotlinx.coroutines.flow.Flow

/**
 * Use case to request a list of upcoming event summaries
 */
interface GetUpcomingEventSummariesUseCase {

    fun invoke(leagueSlug: String): Flow<Result>

    sealed class Result {
        data class Success(val events: List<EventSummary>) : Result()

        data class Error(val errorMessage: String?) : Result()
    }
}
