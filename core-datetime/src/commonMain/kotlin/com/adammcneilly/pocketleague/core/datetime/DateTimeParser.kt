package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * A helper class for parsing an ISO friendly string into a date.
 */
class DateTimeParser {
    /**
     * Converts the supplied [isoString] into a LocalDateTime or returning today as a default.
     */
    fun parseOrToday(
        isoString: String?,
        timeZone: TimeZone = TimeZone.UTC,
    ): LocalDateTime {
        val today = Clock.System.now()

        val parsedDate = isoString?.let(Instant.Companion::parse)

        return (parsedDate ?: today).toLocalDateTime(timeZone)
    }
}
