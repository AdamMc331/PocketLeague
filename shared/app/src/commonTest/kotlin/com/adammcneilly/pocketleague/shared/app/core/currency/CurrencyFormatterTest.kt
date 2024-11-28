package com.adammcneilly.pocketleague.shared.app.core.currency

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class CurrencyFormatterTest {
    private val currencyFormatter = currencyFormatter()

    @Test
    fun formatUSD() {
        val formatted = currencyFormatter.formatCurrency(
            amount = 10.0,
            currency = "USD",
        )

        assertThat(formatted).isEqualTo("$10.00")
    }

    @Test
    fun formatEUR() {
        val formatted = currencyFormatter.formatCurrency(
            amount = 10.0,
            currency = "EUR",
        )

        assertThat(formatted).isEqualTo("€10.00")
    }
}
