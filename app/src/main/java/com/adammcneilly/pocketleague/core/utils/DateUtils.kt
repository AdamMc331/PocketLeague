package com.adammcneilly.pocketleague.core.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A concrete implementation of a [DateTimeHelper] that will do date formatting.
 */
object DateUtils : DateTimeHelper {
    private const val SERIES_DAY_TIME_FORMAT = "MMMM dd, yyyy - HH:mm"

    override fun getSeriesDayTimeString(date: LocalDateTime): String {
        return DateTimeFormatter.ofPattern(SERIES_DAY_TIME_FORMAT).format(date)
    }
}
