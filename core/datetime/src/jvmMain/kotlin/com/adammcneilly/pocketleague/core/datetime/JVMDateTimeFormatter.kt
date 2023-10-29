package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter

/**
 * See commonMain documentation.
 */
class JVMDateTimeFormatter : com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter {
    /**
     * See commonMain documentation.
     */
    override fun formatUTCString(
        utcString: String,
        formatPattern: String,
        timeZone: TimeZone,
    ): String? {
        val instant = Instant.parse(utcString)

        val localDateTime = instant.toLocalDateTime(timeZone.toKotlinTimeZone())

        val dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern)

        return localDateTime.toJavaLocalDateTime().format(dateTimeFormatter)
    }
}
