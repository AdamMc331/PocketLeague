package com.adammcneilly.pocketleague.shared.eventsummarylist.state

import com.adammcneilly.pocketleague.shared.core.ui.UIText
import com.adammcneilly.pocketleague.shared.eventsummarylist.EventSummaryListSort
import com.adammcneilly.pocketleague.shared.eventsummarylist.ui.EventSummaryListItemDisplayModel

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
