package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.TimeZone
import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventRegion
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.core.models.EventTier
import com.adammcneilly.pocketleague.shared.app.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeZone
import com.adammcneilly.pocketleague.shared.app.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.models.Event
import com.adammcneilly.pocketleague.shared.app.core.models.EventRegion
import com.adammcneilly.pocketleague.shared.app.core.models.EventStage
import com.adammcneilly.pocketleague.shared.app.core.models.EventTier

private const val EVENT_DATE_FORMAT = "MMM dd, yyyy"

/**
 * Displays detailed information about an [Event] in a user friendly fashion.
 */
data class EventDetailDisplayModel(
    val eventId: String,
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
            eventId = "",
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

private fun String.toEventDate(
    dateTimeFormatter: DateTimeFormatter,
): String? {
    return dateTimeFormatter.formatUTCString(
        utcString = this,
        formatPattern = EVENT_DATE_FORMAT,
        timeZone = TimeZone.SYSTEM_DEFAULT,
    )
}

private fun String.toEventMode(): String {
    return when (this) {
        "1" -> "1v1"
        "2" -> "2v2"
        "3" -> "3v3"
        else -> this
    }
}

private fun Boolean.toLanOrOnline(): String {
    return if (this) {
        "LAN"
    } else {
        "ONLINE"
    }
}

/**
 * Converts an [Event] into an [EventDetailDisplayModel].
 */
fun Event.toDetailDisplayModel(): EventDetailDisplayModel {
    val dateTimeFormatter = dateTimeFormatter()

    return EventDetailDisplayModel(
        startDate = this.startDateUTC.toEventDate(dateTimeFormatter),
        endDate = this.endDateUTC.toEventDate(dateTimeFormatter),
        name = this.name,
        eventId = this.id,
        stageSummaries = this.stages.sortedBy {
            it.startDateUTC
        }.map(EventStage::toSummaryDisplayModel),
        lightThemeImageUrl = this.imageURL,
        tier = this.tier.toDisplayModel(),
        region = this.region.toDisplayModel(),
        mode = this.mode.toEventMode(),
        onlineOrLAN = this.lan.toLanOrOnline(),
        prize = this.prize?.toDisplayModel(),
    )
}
