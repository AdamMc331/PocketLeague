package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateFormatter

actual class DateTimeFormatter actual constructor() {

    actual fun formatLocalDate(
        localDate: LocalDate,
        formatPattern: String,
    ): String? {
        val nsComponents = localDate.toNSDateComponents()
        val nsDate = NSCalendar.currentCalendar.dateFromComponents(nsComponents)

        val formatter = NSDateFormatter().apply {
            dateFormat = formatPattern
        }

        return nsDate?.let { date ->
            formatter.stringFromDate(date)
        }
    }
}
