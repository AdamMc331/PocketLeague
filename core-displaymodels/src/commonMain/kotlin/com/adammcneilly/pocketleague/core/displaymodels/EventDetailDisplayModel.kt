package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage

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
)

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
        stageSummaries = this.stages?.map(EventStage::toSummaryDisplayModel).orEmpty(),
    )
}
