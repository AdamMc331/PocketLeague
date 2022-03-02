package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.LocalDate

/**
 * A shared class between platforms that is used to format a date into a user friendly string.
 */
expect class DateTimeFormatter constructor() {

    /**
     * Given a [localDate], apply the [formatPattern] to it to convert it into a user readable string.
     *
     * @return The user friendly string for the [localDate], or null if unable to parse correctly.
     */
    fun formatLocalDate(
        localDate: LocalDate,
        formatPattern: String,
    ): String?
}
