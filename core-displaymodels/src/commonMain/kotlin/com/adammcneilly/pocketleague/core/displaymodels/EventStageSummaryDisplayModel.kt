package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.models.EventStage

private const val STAGE_DATE_FORMAT = "MMM dd, yyyy"

/**
 * Displays summary information about an [EventStage] in a user friendly fashion.
 */
data class EventStageSummaryDisplayModel(
    val stageId: String = "",
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val lan: Boolean = false,
    val liquipedia: String = "",
) {

    val dateString: String
        get() = "$startDate – $endDate"
}

/**
 * Converts an [EventStage] into an [EventStageSummaryDisplayModel].
 */
fun EventStage.toSummaryDisplayModel(): EventStageSummaryDisplayModel {
    val dateTimeFormatter = DateTimeFormatter()

    return EventStageSummaryDisplayModel(
        startDate = this.startDate?.let { startDate ->
            dateTimeFormatter.formatLocalDateTime(
                localDateTime = startDate,
                formatPattern = STAGE_DATE_FORMAT,
            )
        }.orEmpty(),
        endDate = this.endDate?.let { endDate ->
            dateTimeFormatter.formatLocalDateTime(
                localDateTime = endDate,
                formatPattern = STAGE_DATE_FORMAT,
            )
        }.orEmpty(),
        stageId = this.id,
        name = this.name,
        lan = this.lan,
        liquipedia = this.liquipedia,
    )
}
