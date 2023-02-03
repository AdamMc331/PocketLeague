package com.adammcneilly.pocketleague.data.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A DTO that maps to a match list response from the octane.gg API.
 */
@Serializable
data class OctaneGGMatchListResponse(
    @SerialName("matches")
    val matches: List<OctaneGGMatch>? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("perPage")
    val perPage: Int? = null,
    @SerialName("pageSize")
    val pageSize: Int? = null
)
