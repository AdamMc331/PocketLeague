package com.adammcneilly.pocketleague.shared.app.currency

/**
 * Returns the [CurrencyFormatter] instance for this target.
 */
actual fun currencyFormatter(): CurrencyFormatter {
    return IOSCurrencyFormatter()
}

/**
 * A concrete implementation of [CurrencyFormatter] to use on the iOS platform.
 */
class IOSCurrencyFormatter : CurrencyFormatter {
    override fun formatCurrency(
        amount: Double,
        currency: String,
    ): String {
        return "$amount:$currency"
    }
}
