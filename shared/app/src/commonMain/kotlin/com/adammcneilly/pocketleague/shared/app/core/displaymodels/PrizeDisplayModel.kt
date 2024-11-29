package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.currency.CurrencyFormatter
import com.adammcneilly.pocketleague.shared.app.core.models.Prize

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
    constructor(
        prize: Prize,
        currencyFormatter: CurrencyFormatter,
    ) : this(
        prizeAmount = prize.formattedCurrency(currencyFormatter).orEmpty(),
    )

    companion object {
        val placeholder = PrizeDisplayModel(
            prizeAmount = "",
            isPlaceholder = true,
        )
    }
}

private fun Prize.formattedCurrency(
    currencyFormatter: CurrencyFormatter,
): String? {
    return currencyFormatter.formatCurrency(
        amount = this.amount,
        currency = this.currency,
    )
}
