package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Format
import kotlin.test.Test
import kotlin.test.assertEquals

class OctaneGGFormatTest {

    @Test
    fun mapValidFormat() {
        val octaneFormat = OctaneGGFormat(
            type = "best",
            length = 7,
        )

        val domainFormat = octaneFormat.toFormat()

        val expectedFormat = Format(
            type = "best",
            length = 7,
        )

        assertEquals(expectedFormat, domainFormat)
    }

    @Test
    fun mapDefaultFormat() {
        val octaneFormat = OctaneGGFormat()

        val domainFormat = octaneFormat.toFormat()

        val expectedFormat = Format(
            type = "",
            length = 0,
        )

        assertEquals(expectedFormat, domainFormat)
    }
}
