package com.adammcneilly.pocketleague.shared.eventlist

import com.adammcneilly.pocketleague.shared.models.Event
import kotlinx.coroutines.flow.Flow

/**
 * Fetches a list of upcoming [Event] entities. This is not specific to any season, group, tier, etc.
 */
interface GetUpcomingEventsUseCase {

    /**
     * @see [GetUpcomingEventsUseCase].
     */
    operator fun invoke(): Flow<Result>

    /**
     * Defines all possible result types for a [GetUpcomingEventsUseCase].
     */
    sealed class Result {
        /**
         * This will be returned if we can successfully request upcoming [events].
         */
        data class Success(
            val events: List<Event>,
        ) : Result()

        /**
         * Should any [error] occur while requesting upcoming events, this will be returned.
         */
        data class Error(
            val error: Throwable,
        ) : Result()
    }
}
