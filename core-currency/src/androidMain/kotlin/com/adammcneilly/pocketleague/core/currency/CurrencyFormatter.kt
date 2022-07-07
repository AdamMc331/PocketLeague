package com.adammcneilly.pocketleague.core.currency

/**
 * Returns the [CurrencyFormatter] instance for this target.
 */
actual fun currencyFormatter(): CurrencyFormatter {
    return JvmCurrencyFormatter()
}
