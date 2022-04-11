package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
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
}
