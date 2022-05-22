package com.adammcneilly.pocketleague.core.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class mapping to a location from the octane.gg API.
 */
@Serializable
data class OctaneGGLocation(
    @SerialName("city")
    val city: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("venue")
    val venue: String? = null,
)
