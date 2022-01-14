package com.adammcneilly.pocketleague.eventsummary.ui

import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary

/**
 * A collection of possible view states for [EventSummaryListScreen].
 */
data class EventSummaryListViewState(
    val showLoading: Boolean = true,
    val events: List<EventSummaryDisplayModel> = emptyList(),
    val selectedEvent: EventSummary? = null,
    val errorMessage: UIText? = null,
)
