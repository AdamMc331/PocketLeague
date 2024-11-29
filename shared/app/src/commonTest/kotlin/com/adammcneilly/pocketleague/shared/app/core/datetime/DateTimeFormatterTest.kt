package com.adammcneilly.pocketleague.shared.app.core.datetime

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class DateTimeFormatterTest {
    private val dateTimeFormatter = dateTimeFormatter()

    @Test
    fun `format utc string with date and time`() {
        val now = "2024-07-18T12:00:00Z"
        val format = "MMM dd, yyyy HH:mm"

        assertThat(dateTimeFormatter.formatUTCString(now, format, TimeZone.UTC))
            .isEqualTo("Jul 18, 2024 12:00")
    }

    @Test
    fun `isBeforeNow returns true for past date`() {
        val pastDate = "2023-07-18T12:00:00Z"
        val now = "2024-07-18T12:00:00Z"

        val timeProvider = DebugTimeProvider(now)

        assertThat(DateTimeFormatter.isBeforeNow(pastDate, timeProvider))
            .isTrue()
    }

    @Test
    fun `isBeforeNow returns false for future date`() {
        val futureDate = "2023-07-18T12:00:00Z"
        val now = "2022-07-18T12:00:00Z"

        val timeProvider = DebugTimeProvider(now)

        assertThat(DateTimeFormatter.isBeforeNow(futureDate, timeProvider))
            .isFalse()
    }

    @Test
    fun `isBeforeNow returns false for same date`() {
        val now = "2022-07-18T12:00:00Z"

        val timeProvider = DebugTimeProvider(now)

        assertThat(DateTimeFormatter.isBeforeNow(now, timeProvider))
            .isFalse()
    }

    @Test
    fun `getRelativeTimestamp within minutes`() {
        val pastDate = "2023-07-18T11:55:00Z"
        val now = "2023-07-18T12:00:00Z"

        val timeProvider = DebugTimeProvider(now)

        assertThat(DateTimeFormatter.getRelativeTimestamp(pastDate, timeProvider))
            .isEqualTo("5m ago")
    }

    @Test
    fun `getRelativeTimestamp within hours`() {
        val pastDate = "2023-07-18T05:55:00Z"
        val now = "2023-07-18T12:00:00Z"

        val timeProvider = DebugTimeProvider(now)

        assertThat(DateTimeFormatter.getRelativeTimestamp(pastDate, timeProvider))
            .isEqualTo("6h ago")
    }

    @Test
    fun `getRelativeTimestamp within days`() {
        val pastDate = "2023-07-12T11:55:00Z"
        val now = "2023-07-18T12:00:00Z"

        val timeProvider = DebugTimeProvider(now)

        assertThat(DateTimeFormatter.getRelativeTimestamp(pastDate, timeProvider))
            .isEqualTo("6d ago")
    }

    @Test
    fun `getRelativeTimestamp within years`() {
        val pastDate = "2022-07-18T11:55:00Z"
        val now = "2023-07-18T12:00:00Z"

        val timeProvider = DebugTimeProvider(now)

        assertThat(DateTimeFormatter.getRelativeTimestamp(pastDate, timeProvider))
            .isEqualTo("365d ago")
    }

    @Test
    fun `getRelativeTimestamp returns null in future`() {
        val futureDate = "2024-07-18T11:55:00Z"
        val now = "2023-07-18T12:00:00Z"

        val timeProvider = DebugTimeProvider(now)

        assertThat(DateTimeFormatter.getRelativeTimestamp(futureDate, timeProvider))
            .isNull()
    }
}
