package com.adammcneilly.pocketleague.core.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Returns a list of [games] from the API.
 */
@Serializable
data class OctaneGGGameListResponse(
    @SerialName("games")
    val games: List<OctaneGGGame>? = null,
)
