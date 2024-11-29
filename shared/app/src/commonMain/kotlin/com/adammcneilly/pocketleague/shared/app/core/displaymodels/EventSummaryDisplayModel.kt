package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeZone
import com.adammcneilly.pocketleague.shared.app.core.models.Event
import com.adammcneilly.pocketleague.shared.app.core.models.EventStage

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
 * @property[dateRange] A user friendly representation of the entire range of this event.
 * @property[isMajor] Whether or not this event is an in person LAN event with international competition.
 * @property[isPlaceholder] If true, we render this display model in a placeholder format
 * @property[winningTeam] If this event is over, we can pass in the [TeamOverviewDisplayModel] of the team that won
 * and highlight appropriately.
 * @param[location] If available, the city/state/country for this LAN event. ex: Boston, USA.
 */
data class EventSummaryDisplayModel(
    val eventId: String,
    val imageURL: ThemedImageURL,
    val name: String,
    val dateRange: String,
    val isMajor: Boolean = false,
    val isPlaceholder: Boolean = false,
    val winningTeam: TeamOverviewDisplayModel? = null,
    private val location: LocationDisplayModel? = null,
) {
    constructor(event: Event, dateTimeFormatter: DateTimeFormatter) : this(
        name = event.name,
        imageURL = ThemedImageURL(
            lightThemeImageURL = event.imageURL,
        ),
        eventId = event.id,
        isMajor = event.lan,
        location = event.location(),
        dateRange = parseDateRange(
            formattedStartDate = event.startDateUTC?.toEventDate(dateTimeFormatter).orEmpty(),
            formattedEndDate = event.endDateUTC?.toEventDate(dateTimeFormatter).orEmpty(),
        ),
    )

    val arenaLocation: String
        get() = listOfNotNull(location?.venue, location?.cityCountry).joinToString(" – ")

    companion object {
        val placeholder = EventSummaryDisplayModel(
            eventId = "",
            imageURL = ThemedImageURL(),
            dateRange = "",
            name = "",
            isPlaceholder = true,
        )
    }
}

/**
 * It's unlikely that an event had more than one location, but we'll default to the
 * last one because it's most likely the main stage if so.
 */
private fun Event.location(): LocationDisplayModel? {
    val lastLocation = this.stages
        .mapNotNull(EventStage::location)
        .lastOrNull()

    return lastLocation?.let(::LocationDisplayModel)
}

private fun String.toEventDate(
    dateTimeFormatter: DateTimeFormatter,
): String? {
    return dateTimeFormatter.formatUTCString(
        utcString = this,
        formatPattern = EVENT_DATE_FORMAT,
        timeZone = TimeZone.SYSTEM_DEFAULT,
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
