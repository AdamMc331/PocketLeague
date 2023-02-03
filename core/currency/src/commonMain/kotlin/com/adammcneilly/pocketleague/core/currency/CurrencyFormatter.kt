package com.adammcneilly.pocketleague.core.currency

/**
 * Create a platform specific implementation of a [CurrencyFormatter].
 */
expect fun currencyFormatter(): CurrencyFormatter

/**
 * Formatting helpers for converting a number to its currency string.
 */
interface CurrencyFormatter {

    /**
     * Consumes currency information and converts them to a string.
     *
     * @param[amount] The number amount of currency, such as 100.0.
     * @param[currency] The type of currency to render, such as "USD".
     */
    fun formatCurrency(
        amount: Double,
        currency: String
    ): String?
}
