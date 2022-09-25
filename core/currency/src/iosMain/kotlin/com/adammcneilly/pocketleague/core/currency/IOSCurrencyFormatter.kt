package com.adammcneilly.pocketleague.core.currency

/**
 * A concrete implementation of [CurrencyFormatter] to use on the iOS platform.
 */
class IOSCurrencyFormatter : CurrencyFormatter {

    override fun formatCurrency(amount: Double, currency: String): String? {
        return "$amount:$currency"
    }
}
