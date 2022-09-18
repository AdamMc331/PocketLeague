package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateFormatter

/**
 * See commonMain documentation.
 */
actual class DateTimeFormatter actual constructor() {

    /**
     * See commonMain documentation.
     */
    actual fun formatLocalDateTime(
        localDateTime: LocalDateTime,
        formatPattern: String,
    ): String? {
        val nsComponents = localDateTime.toNSDateComponents()
        val nsDate = NSCalendar.currentCalendar.dateFromComponents(nsComponents)

        val formatter = NSDateFormatter().apply {
            dateFormat = formatPattern
        }

        return nsDate?.let { date ->
            formatter.stringFromDate(date)
        }
    }

    /**
     * See commonMain documentation.
     */
    actual fun formatInstant(
        instant: Instant,
        formatPattern: String,
        timeZone: TimeZone,
    ): String? {
        val localDateTime = instant.toLocalDateTime(timeZone)

        return formatLocalDateTime(
            localDateTime = localDateTime,
            formatPattern = formatPattern,
        )
    }
}
