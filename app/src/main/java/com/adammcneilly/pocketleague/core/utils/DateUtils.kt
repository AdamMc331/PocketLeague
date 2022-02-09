package com.adammcneilly.pocketleague.core.utils

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * A concrete implementation of a [DateTimeHelper] that will do date formatting.
 */
object DateUtils : DateTimeHelper {
    private const val SERIES_DAY_TIME_FORMAT = "MMMM dd, yyyy - HH:mm"

    private const val EVENT_DAY_FORMAT = "MMM dd, yyyy"

    override fun getSeriesDayTimeString(date: LocalDateTime): String {
        return DateTimeFormatter.ofPattern(SERIES_DAY_TIME_FORMAT).format(date)
    }

    override fun getEventDayString(epochSeconds: Long): String {
        val zonedDateTime = Instant
            .ofEpochSecond(epochSeconds)
            .atOffset(ZoneOffset.UTC)
            .toZonedDateTime()

        return DateTimeFormatter.ofPattern(EVENT_DAY_FORMAT).format(zonedDateTime)
    }
}
