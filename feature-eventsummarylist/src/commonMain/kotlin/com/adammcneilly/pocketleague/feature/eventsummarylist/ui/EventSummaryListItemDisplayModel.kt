package com.adammcneilly.pocketleague.feature.eventsummarylist.ui

/**
 * A user friendly summary of an [EventSummary] to be displayed on the UI.
 */
data class EventSummaryListItemDisplayModel(
    val eventId: String,
    val startDate: String,
    val tournamentName: String,
    val eventName: String,
    val subtitle: String?,
    val imageUrl: String?,
) {

    companion object {
        const val START_DATE_FORMAT = "MMM dd, yyyy"
    }
}
