package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Prize
import kotlin.test.Test
import kotlin.test.assertEquals

class OctaneGGPrizeTest {

    @Test
    fun mapFromValidPrize() {
        val octanePrize = OctaneGGPrize(
            amount = 1.23,
            currency = "currency"
        )

        val expectedPrize = Prize(
            amount = 1.23,
            currency = "currency"
        )

        val domainPrize = octanePrize.toPrize()

        assertEquals(
            expected = expectedPrize,
            actual = domainPrize
        )
    }

    @Test
    fun mapFromDefaultPrize() {
        val octanePrize = OctaneGGPrize()

        val expectedPrize = Prize(
            amount = 0.0,
            currency = ""
        )

        val domainPrize = octanePrize.toPrize()

        assertEquals(
            expected = expectedPrize,
            actual = domainPrize
        )
    }
}
