package com.adammcneilly.pocketleague.shared.app.core.currency

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterCurrencyStyle

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
        formatter.numberStyle = NSNumberFormatterCurrencyStyle
        formatter.currencyCode = currency
        return formatter.stringFromNumber(NSNumber(amount))
    }
}
