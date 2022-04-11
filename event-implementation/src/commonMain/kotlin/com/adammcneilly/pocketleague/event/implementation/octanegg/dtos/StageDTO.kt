package com.adammcneilly.pocketleague.event.implementation.octanegg.dtos

import kotlinx.serialization.Serializable

/**
 * A data class mapping to a stage from the octane.gg API.
 */
@Serializable
data class StageDTO(
    val _id: Int,
    val endDate: String? = null,
    val name: String? = null,
    val startDate: String? = null,
    val prize: PrizeDTO? = null,
    val location: LocationDTO? = null,
    val lan: Boolean? = null,
    val liquipedia: String? = null,
    val region: String? = null,
)
