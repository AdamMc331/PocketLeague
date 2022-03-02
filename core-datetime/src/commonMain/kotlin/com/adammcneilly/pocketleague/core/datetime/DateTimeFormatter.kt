package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.LocalDate

expect class DateTimeFormatter constructor() {
    fun formatLocalDate(
        localDate: LocalDate,
        formatPattern: String,
    ): String?
}
