package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.EventTier
import kotlinx.datetime.TimeZone

private const val EVENT_DATE_FORMAT = "MMM dd, yyyy"

/**
 * Displays detailed information about an [Event] in a user friendly fashion.
 */
data class EventDetailDisplayModel(
    val eventId: Event.Id,
    val name: String,
    val startDate: String,
    val endDate: String,
    val lightThemeImageUrl: String?,
    val tier: EventTierDisplayModel,
    val mode: String,
    val region: EventRegionDisplayModel,
    val onlineOrLAN: String,
    val prize: PrizeDisplayModel?,
    val stageSummaries: List<EventStageSummaryDisplayModel>,
    val darkThemeImageUrl: String? = lightThemeImageUrl,
    val isPlaceholder: Boolean = false,
) {
    companion object {
        val placeholder = EventDetailDisplayModel(
            eventId = Event.Id(""),
            name = "",
            startDate = "",
            endDate = "",
            lightThemeImageUrl = null,
            tier = EventTier.Unknown.toDisplayModel(),
            mode = "",
            region = EventRegion.Unknown.toDisplayModel(),
            onlineOrLAN = "",
            prize = null,
            stageSummaries = listOf(
                EventStageSummaryDisplayModel.placeholder,
                EventStageSummaryDisplayModel.placeholder,
                EventStageSummaryDisplayModel.placeholder,
            ),
            isPlaceholder = true,
        )
    }
}

/**
 * Converts an [Event] into an [EventDetailDisplayModel].
 */
fun Event.toDetailDisplayModel(): EventDetailDisplayModel {
    val dateTimeFormatter = dateTimeFormatter()

    return EventDetailDisplayModel(
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
        eventId = this.id,
        stageSummaries = this.stages.sortedBy {
            it.startDateUTC
        }.map(EventStage::toSummaryDisplayModel),
        lightThemeImageUrl = this.imageURL,
        tier = this.tier.toDisplayModel(),
        region = this.region.toDisplayModel(),
        mode = when (this.mode) {
            "1" -> "1v1"
            "2" -> "2v2"
            "3" -> "3v3"
            else -> this.mode
        },
        onlineOrLAN = if (this.lan) {
            "LAN"
        } else {
            "ONLINE"
        },
        prize = this.prize?.toDisplayModel(),
    )
}
