package com.adammcneilly.pocketleague.event.implementation.octanegg.dtos

import kotlinx.serialization.Serializable

/**
 * A data class mapping to a prize entity from the octane.gg API.
 */
@Serializable
data class PrizeDTO(
    val amount: Double? = null,
    val currency: String? = null,
)
