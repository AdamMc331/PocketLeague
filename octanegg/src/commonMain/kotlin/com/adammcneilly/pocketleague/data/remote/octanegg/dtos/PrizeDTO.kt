package com.adammcneilly.pocketleague.data.remote.octanegg.dtos

import kotlinx.serialization.Serializable

/**
 * A data class mapping to a prize entity from the octane.gg API.
 */
@Serializable
data class PrizeDTO(
    val amount: Double? = null,
    val currency: String? = null,
)
