package com.adammcneilly.pocketleague.eventsummary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.event.api.EventRepository
import com.adammcneilly.pocketleague.eventsummary.EventSummaryListAction
import com.adammcneilly.pocketleague.eventsummary.eventSummaryListStateMutator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A state management container for the [EventSummaryListScreen].
 */
@HiltViewModel
class EventSummaryListViewModel @Inject constructor(
    eventRepository: EventRepository,
) : ViewModel() {

    private val mutator = eventSummaryListStateMutator(
        scope = viewModelScope,
        eventRepository = eventRepository,
    )

    val viewState = mutator.state

    init {
        val fetchAction = EventSummaryListAction.FetchUpcomingEvents(
            leagueSlug = "rlcs-2021-22-1",
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
}
