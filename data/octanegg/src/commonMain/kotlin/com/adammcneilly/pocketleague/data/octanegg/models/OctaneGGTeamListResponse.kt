package com.adammcneilly.pocketleague.data.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data response for a list of teams within the octane.gg API.
 */
@Serializable
data class OctaneGGTeamListResponse(
    @SerialName("teams")
    val teams: List<OctaneGGTeamDetail>? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("perPage")
    val perPage: Int? = null,
    @SerialName("pageSize")
    val pageSize: Int? = null
)
