package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.datetime.TimeZone
import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage

private const val EVENT_DATE_FORMAT = "MMM dd, yyyy"

/**
 * A class that represents summary information about an [Event] in a user friendly fashion.
 *
 * @property[eventId] A unique identifier for this event.
 * @property[imageURL] The remote image URLs for this event.
 * @property[name] A description of this Rocket League event. If we're viewing a regional event for the current season, this should only
 * include the split, region, and event information. For example: OCE Winter Invitational.
 * If we're viewing a major or world championship, we can show the full name. Example: RLCS 2022-23 Spring Major,
 * RLCS 2022-23 World Championship.
 * @property[startDate] A user friendly string representing the date that an event starts.
 * @property[endDate] A user friendly string representing the date that an event ends.
 * @property[dateRange] A user friendly representation of the entire range of this event.
 * @property[isMajor] Whether or not this event is an in person LAN event with international competition.
 * @property[isPlaceholder] If true, we render this display model in a placeholder format
 * @property[winningTeam] If this event is over, we can pass in the [TeamOverviewDisplayModel] of the team that won
 * and highlight appropriately.
 * @property[location] If available, the city/state/country for this LAN event. ex: Boston, USA.
 */
data class EventSummaryDisplayModel(
    val eventId: Event.Id,
    val imageURL: ThemedImageURL,
    val name: String,
    @Deprecated("Supply date range directly.") val startDate: String = "",
    @Deprecated("Supply date range directly.") val endDate: String = "",
    val dateRange: String = "$startDate – $endDate",
    val isMajor: Boolean = false,
    val isPlaceholder: Boolean = false,
    val winningTeam: TeamOverviewDisplayModel? = null,
    private val location: LocationDisplayModel? = null,
) {
    companion object {
        val placeholder = EventSummaryDisplayModel(
            eventId = Event.Id(""),
            imageURL = ThemedImageURL(),
            startDate = "",
            endDate = "",
            name = "",
            dateRange = "",
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
fun Event.toSummaryDisplayModel(dateTimeFormatter: DateTimeFormatter): EventSummaryDisplayModel {
    // It's unlikely that an event had more than one location, but we'll default to the
    // last one because it's most likely the main stage if so.
    val location = this.stages
        .mapNotNull(EventStage::location)
        .lastOrNull()
        ?.toDisplayModel()

    val formattedStartDate = this.startDateUTC?.let { startDate ->
        dateTimeFormatter.formatUTCString(
            utcString = startDate,
            formatPattern = EVENT_DATE_FORMAT,
            timeZone = TimeZone.SYSTEM_DEFAULT,
        )
    }.orEmpty()

    val formattedEndDate = this.endDateUTC?.let { endDate ->
        dateTimeFormatter.formatUTCString(
            utcString = endDate,
            formatPattern = EVENT_DATE_FORMAT,
            timeZone = TimeZone.SYSTEM_DEFAULT,
        )
    }.orEmpty()

    return EventSummaryDisplayModel(
        name = this.name,
        imageURL = ThemedImageURL(
            lightThemeImageURL = this.imageURL,
        ),
        eventId = this.id,
        isMajor = this.lan,
        location = location,
        dateRange = parseDateRange(formattedStartDate, formattedEndDate),
    )
}

/**
 * Given a [formattedStartDate] and [formattedEndDate] then we should check if they
 * are in the same month, and if so, we can customize the output a little bit to simplify.
 *
 * If they span months, return the default range.
 */
private fun parseDateRange(
    formattedStartDate: String,
    formattedEndDate: String,
): String {
    val (startMonth, startDay, startYear) = formattedStartDate.replace(",", "").split(" ")
    val (endMonth, endDay, endYear) = formattedEndDate.replace(",", "").split(" ")

    val isSameMonth = (startMonth == endMonth)
    val isSameYear = (startYear == endYear)

    return when {
        formattedStartDate == formattedEndDate -> {
            formattedStartDate
        }
        isSameMonth && isSameYear -> {
            "$startMonth $startDay – $endDay, $startYear"
        }
        isSameYear -> {
            "$startMonth $startDay – $endMonth $endDay, $startYear"
        }
        else -> {
            "$formattedStartDate – $formattedEndDate"
        }
    }
}
