package com.adammcneilly.pocketleague.shared.app.currency

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter

/**
 * Returns the [CurrencyFormatter] instance for this target.
 */
actual fun currencyFormatter(): CurrencyFormatter {
    return IOSCurrencyFormatter()
}

/**
 * A concrete implementation of [CurrencyFormatter] to use on the iOS platform.
 */
private class IOSCurrencyFormatter : CurrencyFormatter {
    override fun formatCurrency(
        amount: Double,
        currency: String,
    ): String? {
        val formatter = NSNumberFormatter()
        formatter.currencyCode = currency
        formatter.currencySymbol = "$"
        return formatter.stringFromNumber(NSNumber(amount))
    }
}
