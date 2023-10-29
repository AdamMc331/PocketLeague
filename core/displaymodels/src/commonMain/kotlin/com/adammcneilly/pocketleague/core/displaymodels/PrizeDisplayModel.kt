package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.currency.CurrencyFormatter
import com.adammcneilly.pocketleague.core.models.Prize

/**
 * A user friendly representation of a prize.
 *
 * @property[prizeAmount] Is the user readable explanation of the prize, such as $100,000 or €100.000.
 * @property[isPlaceholder] True if this display models is just a placeholder until data is available.
 */
data class PrizeDisplayModel(
    val prizeAmount: String,
    val isPlaceholder: Boolean = false,
) {
    companion object {
        val placeholder = PrizeDisplayModel(
            prizeAmount = "",
            isPlaceholder = true,
        )
    }
}

/**
 * Converts a [Prize] to it's user friendly representation. In the future, we'll need to update this
 * so that it actually converts the currency.
 */
fun Prize.toDisplayModel(
    currencyFormatter: CurrencyFormatter = com.adammcneilly.pocketleague.core.currency.currencyFormatter(),
): PrizeDisplayModel {
    return PrizeDisplayModel(
        prizeAmount = currencyFormatter.formatCurrency(
            amount = this.amount,
            currency = this.currency,
        ).orEmpty(),
    )
}
