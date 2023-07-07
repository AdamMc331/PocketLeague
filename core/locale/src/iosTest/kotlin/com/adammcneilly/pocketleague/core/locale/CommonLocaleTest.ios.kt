package com.adammcneilly.pocketleague.core.locale

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class CommonLocaleTest {

    @Test
    fun getUnitedStatesDisplayName() {
        val countryCode = "us"
        val output = getCountryDisplayName(countryCode)
        assertThat(output).isEqualTo("United States")
    }

    @Test
    fun getGermanyDisplayName() {
        val countryCode = "de"
        val output = getCountryDisplayName(countryCode)
        assertThat(output).isEqualTo("Germany")
    }
}
