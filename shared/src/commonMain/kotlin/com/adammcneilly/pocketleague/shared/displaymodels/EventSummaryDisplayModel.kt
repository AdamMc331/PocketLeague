package com.adammcneilly.pocketleague.shared.displaymodels

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.shared.datetime.DateTimeFormatter

/**
 * Provides summary information about an event in a user friendly manner.
 */
data class EventSummaryDisplayModel(
    val eventId: String = "",
    val startDate: String = "",
    val name: String = "",
    val imageUrl: String? = null,
) {

    companion object {
        const val START_DATE_FORMAT = "MMM dd, yyyy"
    }
}

/**
 * Converts an [Event] entity to the user friendly [EventSummaryDisplayModel].
 */
fun Event.toSummaryDisplayModel(
    dateTimeFormatter: DateTimeFormatter = DateTimeFormatter(),
): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        startDate = this.startDate?.let { startDate ->
            dateTimeFormatter.formatLocalDateTime(
                localDateTime = startDate,
                formatPattern = EventSummaryDisplayModel.START_DATE_FORMAT,
            )
        }.orEmpty(),
        name = this.name,
        imageUrl = this.imageUrl,
        eventId = this.id,
    )
}
