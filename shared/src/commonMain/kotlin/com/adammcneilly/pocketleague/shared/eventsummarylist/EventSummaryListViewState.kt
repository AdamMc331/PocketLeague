package com.adammcneilly.pocketleague.shared.eventsummarylist

import com.adammcneilly.pocketleague.shared.core.ui.UIText

/**
 * A collection of possible view states for [EventSummaryListScreen].
 */
data class EventSummaryListViewState(
    val showLoading: Boolean = true,
    val events: List<EventSummaryListItemDisplayModel> = emptyList(),
    val selectedEventId: String? = null,
    val errorMessage: UIText? = null,
    val currentSort: EventSummaryListSort = EventSummaryListSort.UPCOMING,
)
