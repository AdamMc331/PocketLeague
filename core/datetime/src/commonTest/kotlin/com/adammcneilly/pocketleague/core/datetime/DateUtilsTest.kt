package com.adammcneilly.pocketleague.core.datetime

import com.adammcneilly.pocketleague.core.datetime.test.FakeClock
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

class DateUtilsTest {

    private val fakeClock = FakeClock()

    @Test
    fun getRelativeTimestampMinutesAgo() {
        val now = fakeClock.now()
        val fiveMinutesAgo = now.minus(5.minutes)

        val output = DateUtils.getRelativeTimestamp(
            instant = fiveMinutesAgo,
            clock = fakeClock,
        )

        assertEquals("5m ago", output)
    }

    @Test
    fun getRelativeTimestampHoursAgo() {
        val now = fakeClock.now()
        val fiveHoursAgo = now.minus(5.hours)

        val output = DateUtils.getRelativeTimestamp(
            instant = fiveHoursAgo,
            clock = fakeClock,
        )

        assertEquals("5h ago", output)
    }

    @Test
    fun getRelativeTimestampDaysAgo() {
        val now = fakeClock.now()
        val fiveDaysAgo = now.minus(5.days)

        val output = DateUtils.getRelativeTimestamp(
            instant = fiveDaysAgo,
            clock = fakeClock,
        )

        assertEquals("5d ago", output)
    }
}
