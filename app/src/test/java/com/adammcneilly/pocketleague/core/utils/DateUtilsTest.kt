package com.adammcneilly.pocketleague.core.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.LocalDateTime

class DateUtilsTest {

    @Test
    fun getSeriesDayTimeString() {
        val date = LocalDateTime.of(
            2021,
            10,
            29,
            18,
            30,
        )

        val result = DateUtils.getSeriesDayTimeString(date)
        assertThat(result).isEqualTo("October 29, 2021 - 18:30")
    }
}
