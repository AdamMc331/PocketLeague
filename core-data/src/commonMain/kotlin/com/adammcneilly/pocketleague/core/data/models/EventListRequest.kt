package com.adammcneilly.pocketleague.core.data.models

import kotlinx.datetime.LocalDateTime

/**
 * Defines all of the information that will be passed into a request for a list of events.
 *
 * If the information is null, it won't be used to filter a response at all.
 */
data class EventListRequest(
    val group: String? = null,
    val tiers: List<String>? = null,
    val after: LocalDateTime? = null,
    val before: LocalDateTime? = null,
    val date: LocalDateTime? = null,
)
