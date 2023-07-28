package com.adammcneilly.pocketleague.core.locale

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class LocaleHelperTest {
    private val localeHelper = JVMLocaleHelper()

    @Test
    fun getCountryDisplayName() {
        // Spot check a few based on experience with the app
        assertThat(localeHelper.getCountryDisplayName("us")).isEqualTo("United States")
        assertThat(localeHelper.getCountryDisplayName("de")).isEqualTo("Germany")
    }
}
