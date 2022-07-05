package com.adammcneilly.pocketleague.core.currency

actual fun currencyFormatter(): CurrencyFormatter {
    return JvmCurrencyFormatter()
}
