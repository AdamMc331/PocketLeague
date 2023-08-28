package com.adammcneilly.pocketleague.core.datetime

import com.varabyte.truthish.assertThat
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DateTimeFormatterTest {

    private val dateTimeFormatter = dateTimeFormatter()

    @Test
    fun `isBeforeNow returns true for past date`() {
        val pastDate = "2023-07-18T12:00:00Z"
        val now = "2024-07-18T12:00:00Z"

        val clock = DebugClock(now)

        assertTrue(dateTimeFormatter.isBeforeNow(pastDate, clock))
    }

    @Test
    fun `isBeforeNow returns false for future date`() {
        val futureDate = "2023-07-18T12:00:00Z"
        val now = "2022-07-18T12:00:00Z"

        val clock = DebugClock(now)

        assertFalse(dateTimeFormatter.isBeforeNow(futureDate, clock))
    }

    @Test
    fun `isBeforeNow returns false for same date`() {
        val now = "2022-07-18T12:00:00Z"

        val clock = DebugClock(now)

        assertFalse(dateTimeFormatter.isBeforeNow(now, clock))
    }

    @Test
    fun `getRelativeTimestamp within minutes`() {
        val pastDate = "2023-07-18T11:55:00Z"
        val now = "2023-07-18T12:00:00Z"

        val clock = DebugClock(now)

        assertThat("5m ago").isEqualTo(dateTimeFormatter.getRelativeTimestamp(pastDate, clock))
    }

    @Test
    fun `getRelativeTimestamp within hours`() {
        val pastDate = "2023-07-18T05:55:00Z"
        val now = "2023-07-18T12:00:00Z"

        val clock = DebugClock(now)

        assertThat("6h ago").isEqualTo(dateTimeFormatter.getRelativeTimestamp(pastDate, clock))
    }

    @Test
    fun `getRelativeTimestamp within days`() {
        val pastDate = "2023-07-12T11:55:00Z"
        val now = "2023-07-18T12:00:00Z"

        val clock = DebugClock(now)

        assertThat("6d ago").isEqualTo(dateTimeFormatter.getRelativeTimestamp(pastDate, clock))
    }

    @Test
    fun `getRelativeTimestamp within years`() {
        val pastDate = "2022-07-18T11:55:00Z"
        val now = "2023-07-18T12:00:00Z"

        val clock = DebugClock(now)

        assertThat("365d ago").isEqualTo(dateTimeFormatter.getRelativeTimestamp(pastDate, clock))
    }

    @Test
    fun `getRelativeTimestamp returns null in future`() {
        val futureDate = "2024-07-18T11:55:00Z"
        val now = "2023-07-18T12:00:00Z"

        val clock = DebugClock(now)

        assertThat(dateTimeFormatter.getRelativeTimestamp(futureDate, clock)).isNull()
    }
}
