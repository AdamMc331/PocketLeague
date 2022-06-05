package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

/**
 * See commonMain documentation.
 */
class JvmDateTimeFormatter : com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter {

    /**
     * See commonMain documentation.
     */
    override fun formatInstant(
        instant: Instant,
        formatPattern: String,
        timeZone: TimeZone,
    ): String? {
        val localDateTime = instant.toLocalDateTime(timeZone)

        val dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern)

        return localDateTime.toJavaLocalDateTime().format(dateTimeFormatter)
    }
}
