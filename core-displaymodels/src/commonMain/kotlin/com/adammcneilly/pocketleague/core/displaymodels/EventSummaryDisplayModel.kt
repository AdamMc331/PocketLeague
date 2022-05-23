package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Event

/**
 * A class that represents summary information about an [Event] in a user friendly fashion.
 *
 * @property[eventId] A unique identifier for this event.
 * @property[lightThemeImageUrl] A remote URL for an image for this event, to be shown in a light
 * theme context.
 * @property[darkThemeImageUrl] Same concept as [lightThemeImageUrl], but shown inside a dark theme
 * app.
 * @property[eventStartDate] A user friendly string representing the date that an event starts.
 * @property[eventName] A description of this Rocket League event.
 */
data class EventSummaryDisplayModel(
    val eventId: String = "",
    val lightThemeImageUrl: String? = null,
    val darkThemeImageUrl: String? = lightThemeImageUrl,
    val eventStartDate: String = "",
    val eventName: String = "",
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
        eventStartDate = this.startDate?.let { startDate ->
            dateTimeFormatter.formatLocalDateTime(
                localDateTime = startDate,
                formatPattern = EventSummaryDisplayModel.START_DATE_FORMAT,
            )
        }.orEmpty(),
        eventName = this.name,
        lightThemeImageUrl = this.imageUrl,
        eventId = this.id,
    )
}
