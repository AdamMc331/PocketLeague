package com.adammcneilly.pocketleague.shared.core.datetime

import kotlinx.datetime.LocalDateTime

/**
 * A shared class between platforms that is used to format a date into a user friendly string.
 */
expect class DateTimeFormatter constructor() {

    /**
     * Given a [localDateTime], apply the [formatPattern] to it to convert it into a user readable string.
     *
     * @return The user friendly string for the [localDateTime], or null if unable to parse correctly.
     */
    fun formatLocalDateTime(
        localDateTime: LocalDateTime,
        formatPattern: String,
    ): String?
}
