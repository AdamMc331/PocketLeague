package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

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
        val dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern)

        return localDateTime.toJavaLocalDateTime().format(dateTimeFormatter)
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
