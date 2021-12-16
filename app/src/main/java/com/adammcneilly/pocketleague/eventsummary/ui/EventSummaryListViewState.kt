package com.adammcneilly.pocketleague.eventsummary.ui

import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary

/**
 * A collection of possible view states for [EventSummaryListScreen].
 */
sealed class EventSummaryListViewState {
    /**
     * The initial state of the screen when we are loading events.
     */
    object Loading : EventSummaryListViewState()

    /**
     * The success state of the screen after some [events] have been loaded.
     */
    data class Success(
        val events: List<EventSummary>,
    ) : EventSummaryListViewState()

    /**
     * The failure state when a request to load events is unsuccessful.
     *
     * @property[errorMessage] A user friendly representation of the problem.
     */
    data class Error(
        val errorMessage: UIText,
    ) : EventSummaryListViewState()
}
