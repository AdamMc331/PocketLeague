package com.adammcneilly.pocketleague.core.currency

import java.text.NumberFormat
import java.util.Currency

/**
 * A concrete implementation of [CurrencyFormatter] to use on jvm based platforms.
 */
class AndroidCurrencyFormatter : CurrencyFormatter {

    override fun formatCurrency(amount: Double, currency: String): String? {
        val formatter = NumberFormat.getCurrencyInstance()
        formatter.currency = Currency.getInstance(currency)

        return formatter.format(amount)
    }
}
