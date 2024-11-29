package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeZone
import com.adammcneilly.pocketleague.shared.app.core.models.EventStage

private const val STAGE_DATE_FORMAT = "MMM dd, yyyy"

/**
 * Displays summary information about an [EventStage] in a user friendly fashion.
 */
data class EventStageSummaryDisplayModel(
    val stageId: String,
    val name: String,
    val startDate: String,
    val endDate: String,
    val lan: Boolean,
    val liquipedia: String,
    val isPlaceholder: Boolean = false,
) {
    constructor(
        stage: EventStage,
        dateTimeFormatter: DateTimeFormatter,
    ) : this(
        startDate = stage.startDateUTC?.toStageDate(dateTimeFormatter).orEmpty(),
        endDate = stage.endDateUTC?.toStageDate(dateTimeFormatter).orEmpty(),
        stageId = stage.id,
        name = stage.name,
        lan = stage.lan,
        liquipedia = stage.liquipedia,
    )

    companion object {
        val placeholder = EventStageSummaryDisplayModel(
            stageId = "",
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

private fun String.toStageDate(
    dateTimeFormatter: DateTimeFormatter,
): String? {
    return dateTimeFormatter.formatUTCString(
        utcString = this,
        formatPattern = STAGE_DATE_FORMAT,
        timeZone = TimeZone.SYSTEM_DEFAULT,
    )
}
