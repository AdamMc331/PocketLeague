package com.adammcneilly.pocketleague.core.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class mapping to an Event from the octane.gg API.
 */
@Serializable
data class OctaneGGEvent(
    @SerialName("_id")
    val id: String? = null,
    @SerialName("endDate")
    val endDate: String? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("mode")
    val mode: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("prize")
    val prize: OctaneGGPrize? = null,
    @SerialName("region")
    val region: String? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("stages")
    val stages: List<OctaneGGStage>? = null,
    @SerialName("startDate")
    val startDate: String? = null,
    @SerialName("tier")
    val tier: String? = null,
    @SerialName("groups")
    val groups: List<String>? = null,
)
