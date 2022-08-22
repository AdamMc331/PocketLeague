package com.adammcneilly.pocketleague.data.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Information about a map that a game was played on.
 */
@Serializable
data class OctaneGGMap(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null
)
