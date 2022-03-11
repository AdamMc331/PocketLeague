package com.adammcneilly.pocketleague.shared.eventsummarylist

import com.adammcneilly.pocketleague.shared.ScreenState

/**
 * Defines the view state of our event summary list screen.
 */
data class EventSummaryListState(
    val showLoading: Boolean = true,
    val events: List<EventSummaryDisplayModel> = emptyList(),
    val selectedEventId: String? = null,
    val errorMessage: String? = null,
) : ScreenState
