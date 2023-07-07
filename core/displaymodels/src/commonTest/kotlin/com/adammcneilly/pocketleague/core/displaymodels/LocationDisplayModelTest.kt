package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.Location
import com.varabyte.truthish.assertThat
import kotlin.test.Test

class LocationDisplayModelTest {

    @Test
    fun mapFromLocation() {
        val location = Location(
            venue = "Agganis Arena",
            city = "Boston",
            countryCode = "us",
        )

        val expectedDisplayModel = LocationDisplayModel(
            venue = "Agganis Arena",
            cityCountry = "Boston, us",
        )

        assertThat(location.toDisplayModel()).isEqualTo(expectedDisplayModel)
    }
}
