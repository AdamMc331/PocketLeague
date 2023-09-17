package com.adammcneilly.pocketleague.data.event.api

import com.adammcneilly.pocketleague.core.models.Event

/**
 * Defines a number of ways to request a list of events.
 */
sealed interface EventListRequest {
    /**
     * Request a list of events that occur after the supplied [dateUtc].
     */
    data class AfterDate(
        val dateUtc: String,
    ) : EventListRequest

    /**
     * Request a list of events that occur on the supplied [dateUtc].
     */
    data class OnDate(
        val dateUtc: String,
    ) : EventListRequest

    /**
     * Request a list of events with the given [eventId].
     */
    data class Id(
        val eventId: Event.Id,
    ) : EventListRequest
}
