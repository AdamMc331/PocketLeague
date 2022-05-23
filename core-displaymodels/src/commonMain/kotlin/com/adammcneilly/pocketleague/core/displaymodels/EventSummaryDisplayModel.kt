package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Event

private const val EVENT_DATE_FORMAT = "MMM dd, yyyy"

/**
 * A class that represents summary information about an [Event] in a user friendly fashion.
 *
 * @property[eventId] A unique identifier for this event.
 * @property[lightThemeImageUrl] A remote URL for an image for this event, to be shown in a light
 * theme context.
 * @property[darkThemeImageUrl] Same concept as [lightThemeImageUrl], but shown inside a dark theme
 * app.
 * @property[startDate] A user friendly string representing the date that an event starts.
 * @property[endDate] A user friendly string representing the date that an event ends.
 * @property[name] A description of this Rocket League event.
 */
data class EventSummaryDisplayModel(
    val eventId: String = "",
    val lightThemeImageUrl: String? = null,
    val darkThemeImageUrl: String? = lightThemeImageUrl,
    val startDate: String = "",
    val endDate: String = "",
    val name: String = "",
) {

    val dateString: String
        get() = "$startDate – $endDate"
}

/**
 * Converts an [Event] entity to the user friendly [EventSummaryDisplayModel].
 */
fun Event.toSummaryDisplayModel(): EventSummaryDisplayModel {
    val dateTimeFormatter = DateTimeFormatter()

    return EventSummaryDisplayModel(
        startDate = this.startDate?.let { startDate ->
            dateTimeFormatter.formatLocalDateTime(
                localDateTime = startDate,
                formatPattern = EVENT_DATE_FORMAT,
            )
        }.orEmpty(),
        endDate = this.endDate?.let { endDate ->
            dateTimeFormatter.formatLocalDateTime(
                localDateTime = endDate,
                formatPattern = EVENT_DATE_FORMAT,
            )
        }.orEmpty(),
        name = this.name,
        lightThemeImageUrl = this.imageUrl,
        eventId = this.id,
    )
}
