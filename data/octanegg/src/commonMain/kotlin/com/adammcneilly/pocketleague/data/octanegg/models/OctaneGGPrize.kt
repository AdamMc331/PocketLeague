package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Prize
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
    val currency: String? = null
)

/**
 * Converts an [OctaneGGPrize] to a [Prize] in our domain.
 */
fun OctaneGGPrize.toPrize(): Prize {
    return Prize(
        amount = this.amount ?: 0.0,
        currency = this.currency.orEmpty()
    )
}
