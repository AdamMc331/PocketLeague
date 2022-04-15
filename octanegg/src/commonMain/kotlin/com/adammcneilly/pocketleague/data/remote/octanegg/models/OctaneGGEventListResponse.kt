package com.adammcneilly.pocketleague.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A DTO that maps to an event list response from the octane.gg API.
 */
@Serializable
data class OctaneGGEventListResponse(
    @SerialName("events")
    val events: List<OctaneGGEvent>? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("perPage")
    val perPage: Int? = null,
    @SerialName("pageSize")
    val pageSize: Int? = null,
)
