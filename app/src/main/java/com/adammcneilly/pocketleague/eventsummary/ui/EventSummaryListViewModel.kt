package com.adammcneilly.pocketleague.eventsummary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.feature.eventsummarylist.EventSummaryListSort
import com.adammcneilly.pocketleague.feature.eventsummarylist.domain.GetEventSummariesUseCase
import com.adammcneilly.pocketleague.feature.eventsummarylist.state.EventSummaryListAction
import com.adammcneilly.pocketleague.feature.eventsummarylist.state.eventSummaryListStateMutator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A state management container for the [EventSummaryListScreen].
 */
@HiltViewModel
class EventSummaryListViewModel @Inject constructor(
    getEventsUseCase: GetEventSummariesUseCase,
) : ViewModel() {

    private val mutator = eventSummaryListStateMutator(
        scope = viewModelScope,
        getEventsUseCase = getEventsUseCase,
    )

    val viewState = mutator.state

    init {
        val fetchAction = EventSummaryListAction.FetchEventSummaries(
            request = GetEventSummariesUseCase.Request(),
        )

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

    /**
     * Whenever the user toggles the sort option for the summary list.
     */
    fun sortChanged(sort: EventSummaryListSort) {
        val action = EventSummaryListAction.SelectedSort(sort)

        mutator.accept(action)
    }
}
