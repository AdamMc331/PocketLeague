package com.adammcneilly.pocketleague.feature.eventsummarylist.state

import com.adammcneilly.pocketleague.feature.eventsummarylist.EventSummaryListSort
import com.adammcneilly.pocketleague.feature.eventsummarylist.ui.EventSummaryListItemDisplayModel

/**
 * A collection of possible view states for [EventSummaryListScreen].
 */
data class EventSummaryListViewState(
    val showLoading: Boolean = true,
    val events: List<EventSummaryListItemDisplayModel> = emptyList(),
    val selectedEventId: String? = null,
    val errorMessage: String? = null,
    val currentSort: EventSummaryListSort = EventSummaryListSort.UPCOMING,
)
