package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A data class mapping to a prize entity from the octane.gg API.
 */
@Serializable
data class OctaneGGPrize(
    @SerialName("amount")
    val amount: Double? = null,
    @SerialName("currency")
    val currency: String? = null,
)
