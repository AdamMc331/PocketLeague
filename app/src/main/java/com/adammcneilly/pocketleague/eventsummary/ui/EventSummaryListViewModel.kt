package com.adammcneilly.pocketleague.eventsummary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.eventsummary.domain.state.EventSummaryListAction
import com.adammcneilly.pocketleague.eventsummary.domain.state.eventSummaryListStateMutator
import com.adammcneilly.pocketleague.eventsummary.domain.usecases.FetchUpcomingEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A state management container for the [EventSummaryListScreen].
 */
@HiltViewModel
class EventSummaryListViewModel @Inject constructor(
    fetchUpcomingEventsUseCase: FetchUpcomingEventsUseCase,
    dateTimeHelper: DateTimeHelper,
) : ViewModel() {

    private val mutator = eventSummaryListStateMutator(
        scope = viewModelScope,
        fetchUpcomingEventsUseCase = fetchUpcomingEventsUseCase,
        dateTimeHelper = dateTimeHelper,
    )

    val viewState = mutator.state

    init {
        val fetchAction = EventSummaryListAction.FetchUpcomingEvents
        mutator.accept(fetchAction)
    }

    /**
     * When we navigate to an event overview, we should clear our selected event so that we don't
     * continue to show it.
     */
    fun navigatedToEventOverview() {
        val action = EventSummaryListAction.NavigatedToEventOverview

        mutator.accept(action)
    }

    /**
     * Whenever a user clicks an event we consume that [eventId] and update state accordingly.
     */
    fun eventClicked(eventId: String) {
        val action = EventSummaryListAction.SelectedEvent(eventId)

        mutator.accept(action)
    }
}
