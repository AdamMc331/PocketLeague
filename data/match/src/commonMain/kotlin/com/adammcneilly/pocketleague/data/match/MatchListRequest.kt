package com.adammcneilly.pocketleague.data.match

/**
 * Defines all of the information that will be passed into a request
 * for a list of matches.
 *
 * If the information is null, it won't be used to filter a response at all.
 */
data class MatchListRequest(
    val after: String? = null,
    val before: String? = null,
    val group: String? = null,
    val eventId: String? = null,
    val stageId: String? = null,
    val region: String? = null,
)
