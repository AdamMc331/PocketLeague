package com.adammcneilly.pocketleague.eventsummary.ui

import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary

sealed class EventSummaryListViewState {
    object Loading : EventSummaryListViewState()

    data class Success(
        val events: List<EventSummary>,
    ) : EventSummaryListViewState()

    data class Error(
        val errorMessage: UIText,
    ) : EventSummaryListViewState()
}
