package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.Prize

/**
 * A user friendly representation of a prize.
 *
 * @property[prizeAmount] Is the user readable explanation of the prize, such as $100,000 or €100.000.
 */
data class PrizeDisplayModel(
    val prizeAmount: String = "",
)

/**
 * Converts a [Prize] to it's user friendly representation. In the future, we'll need to update this
 * so that it actually converts the currency.
 */
fun Prize.toDisplayModel(): PrizeDisplayModel {
    return PrizeDisplayModel(
        prizeAmount = "${this.amount} ${this.currency}"
    )
}
