package com.adammcneilly.pocketleague.data.event

import kotlinx.datetime.Instant

/**
 * Defines all of the information that will be passed into a request for a list of events.
 *
 * If the information is null, it won't be used to filter a response at all.
 */
data class EventListRequest(
    val group: String? = null,
    val tiers: List<String>? = null,
    val after: Instant? = null,
    val before: Instant? = null,
    val date: Instant? = null,
    val name: String? = null,
)
