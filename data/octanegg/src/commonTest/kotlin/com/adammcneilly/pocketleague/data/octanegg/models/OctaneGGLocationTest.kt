package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Location
import com.varabyte.truthish.assertThat
import kotlin.test.Test
import kotlin.test.assertEquals

class OctaneGGLocationTest {

    @Test
    fun convertFromValidLocation() {
        val octaneLocation = OctaneGGLocation(
            venue = "Agganis Arena",
            city = "Boston",
            countryCode = "us",
        )

        val expectedLocation = Location(
            venue = "Agganis Arena",
            city = "Boston",
            countryCode = "us",
        )

        assertEquals(expectedLocation, octaneLocation.toLocation())
    }

    @Test
    fun convertFromDefaultLocation() {
        val octaneLocation = OctaneGGLocation()

        val expectedLocation = Location(
            venue = "",
            city = "",
            countryCode = "",
        )

        assertThat(octaneLocation.toLocation()).isEqualTo(expectedLocation)
    }
}
