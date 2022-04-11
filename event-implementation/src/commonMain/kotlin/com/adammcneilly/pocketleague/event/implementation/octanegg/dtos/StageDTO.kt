package com.adammcneilly.pocketleague.event.implementation.octanegg.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class mapping to a stage from the octane.gg API.
 */
@Serializable
internal data class StageDTO(
    @SerialName("_id")
    val id: Int,
    val endDate: String? = null,
    val name: String? = null,
    val startDate: String? = null,
    val prize: PrizeDTO? = null,
    val location: LocationDTO? = null,
    val lan: Boolean? = null,
    val liquipedia: String? = null,
    val region: String? = null,
)
