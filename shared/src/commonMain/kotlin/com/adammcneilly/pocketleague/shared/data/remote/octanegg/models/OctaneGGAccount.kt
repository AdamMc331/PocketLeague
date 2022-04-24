package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a [PlayerDTO] account within the octane.gg API.
 */
@Serializable
data class OctaneGGAccount(
    @SerialName("id")
    val id: String? = null,
    @SerialName("platform")
    val platform: String? = null,
)
