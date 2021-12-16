package com.adammcneilly.pocketleague.eventsummary.ui

import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.core.utils.DateUtils
import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary

/**
 * A user friendly summary of an [EventSummary] to be displayed on the UI.
 */
data class EventSummaryDisplayModel(
    val startDate: String,
    val tournamentName: String,
    val eventName: String,
)

/**
 * converts an [EventSummary] entity to its relevant [EventSummaryDisplayModel].
 */
fun EventSummary.toDisplayModel(
    dateTimeHelper: DateTimeHelper = DateUtils,
): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        startDate = dateTimeHelper.getEventDayString(this.startDate),
        tournamentName = this.tournamentName,
        eventName = this.eventName,
    )
}
