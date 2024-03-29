package com.adammcneilly.pocketleague.core.currency

import kotlin.test.Test
import kotlin.test.assertEquals

class JVMCurrencyFormatterTest {
    private val currencyFormatter = JVMCurrencyFormatter()

    @Test
    fun formatUSD() {
        assertEquals(
            expected = "$10.00",
            actual = currencyFormatter.formatCurrency(
                amount = 10.0,
                currency = "USD",
            ),
        )
    }

    @Test
    fun formatEUR() {
        assertEquals(
            expected = "€10.00",
            actual = currencyFormatter.formatCurrency(
                amount = 10.0,
                currency = "EUR",
            ),
        )
    }
}
