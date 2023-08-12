package com.adammcneilly.pocketleague.core.locale

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class LocaleHelperTest {
    private val localeHelper = provideLocaleHelper()

    @Test
    fun getCountryDisplayName() {
        // Spot check a few based on experience with the app
        assertThat(localeHelper.getCountryDisplayName("us")).isEqualTo("United States")
        assertThat(localeHelper.getCountryDisplayName("de")).isEqualTo("Germany")
    }

    @Test
    fun getCountryFlag() {
        assertThat(localeHelper.getFlagEmoji("US")).isEqualTo("\uD83C\uDDFA\uD83C\uDDF8")
        assertThat(localeHelper.getFlagEmoji("FR")).isEqualTo("\uD83C\uDDEB\uD83C\uDDF7")
    }

    @Test
    fun overrideEnglandFlag() {
        assertThat(
            actual = localeHelper.getFlagEmoji("EN")
        ).isEqualTo(
            expected = "\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F"
        )
    }
}
