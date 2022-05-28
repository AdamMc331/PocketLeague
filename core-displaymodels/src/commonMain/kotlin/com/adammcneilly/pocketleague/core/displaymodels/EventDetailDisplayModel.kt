package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.EventTier

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
    val prize: String = "",
)

/**
 * Converts an [Event] into an [EventDetailDisplayModel].
 */
fun Event.toDetailDisplayModel(): EventDetailDisplayModel {
    val dateTimeFormatter = DateTimeFormatter()

    return EventDetailDisplayModel(
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
        eventId = this.id,
        stageSummaries = this.stages.sortedBy {
            it.startDate
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
        prize = "$100,000",
    )
}
