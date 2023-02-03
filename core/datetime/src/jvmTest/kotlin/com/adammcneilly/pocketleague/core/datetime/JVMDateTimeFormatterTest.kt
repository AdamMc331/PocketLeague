package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

class JVMDateTimeFormatterTest {
    private val dateTimeFormatter = JVMDateTimeFormatter()

    @Test
    fun formatDateTime() {
        val input = "2022-08-03T23:00:00Z"

        val output = dateTimeFormatter.formatUTCString(
            utcString = input,
            formatPattern = "MMM dd, yyyy",
            timeZone = TimeZone.UTC
        )

        assertEquals(
            expected = "Aug 03, 2022",
            actual = output
        )
    }

    @Test
    fun formatExtraTimeOnlySeconds() {
        val input = 55
        val output = dateTimeFormatter.formatExtraTime(input)

        assertEquals("0:55", output)
    }

    @Test
    fun formatExtraTimeOverMinute() {
        val input = 65
        val output = dateTimeFormatter.formatExtraTime(input)

        assertEquals("1:05", output)
    }
}
