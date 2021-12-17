package com.adammcneilly.pocketleague.eventsummary.ui

import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary

/**
 * A user friendly summary of an [EventSummary] to be displayed on the UI.
 */
data class EventSummaryDisplayModel(
    val startDate: String,
    val tournamentName: String,
    val eventName: String,
)
