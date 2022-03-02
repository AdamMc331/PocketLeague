package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

/**
 * See commonMain documentation.
 */
actual class DateTimeFormatter actual constructor() {

    /**
     * See commonMain documentation.
     */
    actual fun formatLocalDate(
        localDate: LocalDate,
        formatPattern: String,
    ): String? {
        val dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern)

        return localDate.toJavaLocalDate().format(dateTimeFormatter)
    }
}
