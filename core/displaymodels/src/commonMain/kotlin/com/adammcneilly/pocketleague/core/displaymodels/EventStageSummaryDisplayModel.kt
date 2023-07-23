package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.core.models.EventStage
import kotlinx.datetime.TimeZone

private const val STAGE_DATE_FORMAT = "MMM dd, yyyy"

/**
 * Displays summary information about an [EventStage] in a user friendly fashion.
 */
data class EventStageSummaryDisplayModel(
    val stageId: EventStage.Id,
    val name: String,
    val startDate: String,
    val endDate: String,
    val lan: Boolean,
    val liquipedia: String,
    val isPlaceholder: Boolean = false,
) {

    companion object {
        val placeholder = EventStageSummaryDisplayModel(
            stageId = EventStage.Id(""),
            name = "",
            startDate = "",
            endDate = "",
            lan = false,
            liquipedia = "",
            isPlaceholder = true,
        )
    }

    val dateString: String
        get() = if (endDate.isNotEmpty()) {
            "$startDate – $endDate"
        } else if (startDate.isNotEmpty()) {
            startDate
        } else {
            "Date Unavailable"
        }
}

/**
 * Converts an [EventStage] into an [EventStageSummaryDisplayModel].
 */
fun EventStage.toSummaryDisplayModel(): EventStageSummaryDisplayModel {
    val dateTimeFormatter = dateTimeFormatter()

    return EventStageSummaryDisplayModel(
        startDate = this.startDateUTC?.let { startDate ->
            dateTimeFormatter.formatUTCString(
                utcString = startDate,
                formatPattern = STAGE_DATE_FORMAT,
                timeZone = TimeZone.currentSystemDefault(),
            )
        }.orEmpty(),
        endDate = this.endDateUTC?.let { endDate ->
            dateTimeFormatter.formatUTCString(
                utcString = endDate,
                formatPattern = STAGE_DATE_FORMAT,
                timeZone = TimeZone.currentSystemDefault(),
            )
        }.orEmpty(),
        stageId = this.id,
        name = this.name,
        lan = this.lan,
        liquipedia = this.liquipedia,
    )
}
