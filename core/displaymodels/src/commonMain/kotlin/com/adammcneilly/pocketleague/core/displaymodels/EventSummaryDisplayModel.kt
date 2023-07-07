package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
import kotlinx.datetime.TimeZone

private const val EVENT_DATE_FORMAT = "MMM dd, yyyy"

/**
 * A class that represents summary information about an [Event] in a user friendly fashion.
 *
 * @property[eventId] A unique identifier for this event.
 * @property[imageURL] The remote image URLs for this event.
 * @property[name] A description of this Rocket League event.
 * @property[startDate] A user friendly string representing the date that an event starts.
 * @property[endDate] A user friendly string representing the date that an event ends.
 * @property[dateRange] A user friendly representation of the entire range of this event.
 * @property[isMajor] Whether or not this event is an in person LAN event with international competition.
 * @property[isPlaceholder] If true, we render this display model in a placeholder format
 * @property[location] If available, the city/state/country for this LAN event. ex: Boston, USA.
 */
data class EventSummaryDisplayModel(
    val eventId: String,
    val imageURL: ThemedImageURL,
    val name: String,
    @Deprecated("Supply date range directly.") val startDate: String = "",
    @Deprecated("Supply date range directly.") val endDate: String = "",
    val dateRange: String = "$startDate – $endDate",
    val isMajor: Boolean = false,
    val isPlaceholder: Boolean = false,
    private val location: LocationDisplayModel? = null,
) {

    companion object {
        val placeholder = EventSummaryDisplayModel(
            eventId = "",
            imageURL = ThemedImageURL(),
            startDate = "",
            endDate = "",
            name = "",
            isPlaceholder = true,
        )
    }

    @Deprecated("Deprecated in favor of new dateRange property.", ReplaceWith("\"\$dateRange\""))
    val dateString: String
        get() = "$startDate – $endDate"

    val arenaLocation: String
        get() = listOfNotNull(location?.venue, location?.cityCountry).joinToString(" – ")
}

/**
 * Converts an [Event] entity to the user friendly [EventSummaryDisplayModel].
 */
fun Event.toSummaryDisplayModel(): EventSummaryDisplayModel {
    return this.toSummaryDisplayModel(dateTimeFormatter())
}

/**
 * Converts an [Event] entity to the user friendly [EventSummaryDisplayModel].
 */
fun Event.toSummaryDisplayModel(
    dateTimeFormatter: DateTimeFormatter,
): EventSummaryDisplayModel {
    // It's unlikely that an event had more than one location, but we'll default to the
    // last one because it's most likely the main stage if so.
    val location = this.stages
        .mapNotNull(EventStage::location)
        .lastOrNull()
        ?.toDisplayModel()

    return EventSummaryDisplayModel(
        startDate = this.startDateUTC?.let { startDate ->
            dateTimeFormatter.formatUTCString(
                utcString = startDate,
                formatPattern = EVENT_DATE_FORMAT,
                timeZone = TimeZone.currentSystemDefault(),
            )
        }.orEmpty(),
        endDate = this.endDateUTC?.let { endDate ->
            dateTimeFormatter.formatUTCString(
                utcString = endDate,
                formatPattern = EVENT_DATE_FORMAT,
                timeZone = TimeZone.currentSystemDefault(),
            )
        }.orEmpty(),
        name = this.name,
        imageURL = ThemedImageURL(
            lightThemeImageURL = this.imageURL,
        ),
        eventId = this.id,
        isMajor = this.lan,
        location = location,
    )
}
