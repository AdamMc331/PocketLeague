package com.adammcneilly.pocketleague.shared.app.data.octanegg.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data response for a list of players within the octane.gg API.
 */
@Serializable
data class OctaneGGPlayerListResponse(
    @SerialName("players")
    val players: List<OctaneGGPlayer>? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("perPage")
    val perPage: Int? = null,
    @SerialName("pageSize")
    val pageSize: Int? = null,
)
