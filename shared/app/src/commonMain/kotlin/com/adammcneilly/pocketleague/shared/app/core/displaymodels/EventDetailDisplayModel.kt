package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.currency.CurrencyFormatter
import com.adammcneilly.pocketleague.shared.app.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeZone
import com.adammcneilly.pocketleague.shared.app.core.models.Event
import com.adammcneilly.pocketleague.shared.app.core.models.EventTier
import com.adammcneilly.pocketleague.shared.app.core.models.Region

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
    val region: RegionDisplayModel,
    val onlineOrLAN: String,
    val prize: PrizeDisplayModel?,
    val stageSummaries: List<EventStageSummaryDisplayModel>,
    val darkThemeImageUrl: String? = lightThemeImageUrl,
    val isPlaceholder: Boolean = false,
) {
    constructor(
        event: Event,
        dateTimeFormatter: DateTimeFormatter,
        currencyFormatter: CurrencyFormatter,
    ) : this(
        startDate = event.startDateUTC?.toEventDate(dateTimeFormatter).orEmpty(),
        endDate = event.endDateUTC?.toEventDate(dateTimeFormatter).orEmpty(),
        name = event.name,
        eventId = event.id,
        stageSummaries = event.stageSummaries(dateTimeFormatter),
        lightThemeImageUrl = event.imageURL,
        tier = EventTierDisplayModel(event.tier),
        region = RegionDisplayModel(event.region),
        mode = event.mode.toEventMode(),
        onlineOrLAN = event.lan.toLanOrOnline(),
        prize = event.prizeDisplayModel(currencyFormatter),
    )

    companion object {
        val placeholder = EventDetailDisplayModel(
            eventId = "",
            name = "",
            startDate = "",
            endDate = "",
            lightThemeImageUrl = null,
            tier = EventTierDisplayModel(EventTier.Unknown),
            mode = "",
            region = RegionDisplayModel(Region.Unknown),
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

private fun Event.stageSummaries(
    dateTimeFormatter: DateTimeFormatter,
): List<EventStageSummaryDisplayModel> {
    return this.stages
        .sortedBy { stage ->
            stage.startDateUTC
        }
        .map { stage ->
            EventStageSummaryDisplayModel(stage, dateTimeFormatter)
        }
}

private fun Event.prizeDisplayModel(
    currencyFormatter: CurrencyFormatter,
): PrizeDisplayModel? {
    return this.prize?.let { prize ->
        PrizeDisplayModel(prize, currencyFormatter)
    }
}
