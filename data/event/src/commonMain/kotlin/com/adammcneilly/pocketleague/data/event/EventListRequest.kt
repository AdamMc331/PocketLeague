package com.adammcneilly.pocketleague.data.event

/**
 * Defines all of the information that will be passed into a request for a list of events.
 *
 * If the information is null, it won't be used to filter a response at all.
 */
data class EventListRequest(
    val group: String? = null,
    val tiers: List<String>? = null,
    val after: String? = null,
    val before: String? = null,
    val date: String? = null,
    val name: String? = null,
)
