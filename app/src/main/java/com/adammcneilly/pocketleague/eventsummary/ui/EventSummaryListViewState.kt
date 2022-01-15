package com.adammcneilly.pocketleague.eventsummary.ui

import com.adammcneilly.pocketleague.core.ui.UIText

/**
 * A collection of possible view states for [EventSummaryListScreen].
 */
data class EventSummaryListViewState(
    val showLoading: Boolean = true,
    val events: List<EventSummaryDisplayModel> = emptyList(),
    val selectedEventId: String? = null,
    val errorMessage: UIText? = null,
)
