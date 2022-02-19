package com.adammcneilly.pocketleague.eventsummary

import com.adammcneilly.pocketleague.core.ui.UIImage

/**
 * A user friendly summary of an [EventSummary] to be displayed on the UI.
 */
data class EventSummaryDisplayModel(
    val eventId: String,
    val startDate: String,
    val tournamentName: String,
    val eventName: String,
    val subtitle: String?,
    val image: UIImage,
)
