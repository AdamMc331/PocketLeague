package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Event
import kotlinx.datetime.TimeZone

private const val EVENT_DATE_FORMAT = "MMM dd, yyyy"

/**
 * A class that represents summary information about an [Event] in a user friendly fashion.
 *
 * @property[eventId] A unique identifier for this event.
 * @property[imageURL] The remote image URLs for this event.
 * @property[startDate] A user friendly string representing the date that an event starts.
 * @property[endDate] A user friendly string representing the date that an event ends.
 * @property[name] A description of this Rocket League event.
 * @property[isPlaceholder] If true, we render this display model in a placeholder format.
 */
data class EventSummaryDisplayModel(
    val eventId: String = "",
    val imageURL: ThemedImageURL = ThemedImageURL(),
    val startDate: String = "",
    val endDate: String = "",
    val name: String = "",
    val isPlaceholder: Boolean = false,
) {

    val dateString: String
        get() = "$startDate – $endDate"
}

/**
 * Converts an [Event] entity to the user friendly [EventSummaryDisplayModel].
 */
fun Event.toSummaryDisplayModel(): EventSummaryDisplayModel {
    val dateTimeFormatter = dateTimeFormatter()

    return EventSummaryDisplayModel(
        startDate = this.startDateUTC?.let { startDate ->
            dateTimeFormatter.formatInstant(
                instant = startDate,
                formatPattern = EVENT_DATE_FORMAT,
                timeZone = TimeZone.currentSystemDefault(),
            )
        }.orEmpty(),
        endDate = this.endDateUTC?.let { endDate ->
            dateTimeFormatter.formatInstant(
                instant = endDate,
                formatPattern = EVENT_DATE_FORMAT,
                timeZone = TimeZone.currentSystemDefault(),
            )
        }.orEmpty(),
        name = this.name,
        imageURL = ThemedImageURL(
            lightThemeImageUrl = this.imageUrl,
        ),
        eventId = this.id,
    )
}
