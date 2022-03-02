package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import java.time.format.DateTimeFormatter

actual class DateTimeFormatter actual constructor() {
    actual fun formatLocalDate(
        localDate: LocalDate,
        formatPattern: String,
    ): String? {
        val dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern)

        return localDate.toJavaLocalDate().format(dateTimeFormatter)
    }
}
