package com.adammcneilly.pocketleague.shared.datalayer.objects

/**
 * Request body for pulling a list of events.
 *
 * @property[upcoming] If true, request events that have not begun yet. False will request past and current events.
 * If not supplied, API will use a default.
 * @property[numEvents] The number of events that should be returned by the API. Will return up to this number based on what's available.
 * If not supplied, API will use a default.
 */
data class EventListRequestBody(
    val upcoming: Boolean? = null,
    val numEvents: Int? = null,
)
