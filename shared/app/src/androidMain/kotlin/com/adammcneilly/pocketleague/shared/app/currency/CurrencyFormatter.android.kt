package com.adammcneilly.pocketleague.shared.app.currency

import java.text.NumberFormat
import java.util.Currency

/**
 * Returns the [CurrencyFormatter] instance for this target.
 */
actual fun currencyFormatter(): CurrencyFormatter {
    return AndroidCurrencyFormatter()
}

/**
 * A concrete implementation of [CurrencyFormatter] to use on Android platforms.
 */
class AndroidCurrencyFormatter : CurrencyFormatter {
    override fun formatCurrency(
        amount: Double,
        currency: String,
    ): String? {
        val formatter = NumberFormat.getCurrencyInstance()
        formatter.currency = Currency.getInstance(currency)

        return formatter.format(amount)
    }
}
