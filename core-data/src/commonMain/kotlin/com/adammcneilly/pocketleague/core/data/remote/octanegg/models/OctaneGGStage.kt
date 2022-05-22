package com.adammcneilly.pocketleague.core.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class mapping to a stage from the octane.gg API.
 */
@Serializable
data class OctaneGGStage(
    @SerialName("_id")
    val id: Int? = null,
    @SerialName("endDate")
    val endDate: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("startDate")
    val startDate: String? = null,
    @SerialName("prize")
    val prize: OctaneGGPrize? = null,
    @SerialName("location")
    val location: OctaneGGLocation? = null,
    @SerialName("lan")
    val lan: Boolean? = null,
    @SerialName("liquipedia")
    val liquipedia: String? = null,
    @SerialName("region")
    val region: String? = null,
)
