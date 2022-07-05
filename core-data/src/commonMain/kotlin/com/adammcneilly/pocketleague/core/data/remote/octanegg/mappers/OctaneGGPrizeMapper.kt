package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGPrize
import com.adammcneilly.pocketleague.core.models.Prize

/**
 * Converts an [OctaneGGPrize] entity to a [Prize] in our domain.
 */
fun OctaneGGPrize.toPrize(): Prize {
    return Prize(
        amount = this.amount ?: 0.0,
        currency = this.currency.orEmpty(),
    )
}
