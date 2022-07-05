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
    val eventId: String = "",
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val stageSummaries: List<EventStageSummaryDisplayModel> = emptyList(),
    val lightThemeImageUrl: String? = null,
    val darkThemeImageUrl: String? = lightThemeImageUrl,
    val tier: EventTierDisplayModel = EventTier.Unknown.toDisplayModel(),
    val mode: String = "",
    val region: EventRegionDisplayModel = EventRegion.Unknown.toDisplayModel(),
    val onlineOrLAN: String = "",
    val prize: PrizeDisplayModel = PrizeDisplayModel(),
)

/**
 * Converts an [Event] into an [EventDetailDisplayModel].
 */
fun Event.toDetailDisplayModel(): EventDetailDisplayModel {
    val dateTimeFormatter = dateTimeFormatter()

    return EventDetailDisplayModel(
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
        eventId = this.id,
        stageSummaries = this.stages.sortedBy {
            it.startDateUTC
        }.map(EventStage::toSummaryDisplayModel),
        lightThemeImageUrl = this.imageUrl,
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
        prize = this.prize?.toDisplayModel() ?: PrizeDisplayModel(),
    )
}
