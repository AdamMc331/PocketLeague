package com.adammcneilly.pocketleague.eventsummary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.shared.eventsummarylist.EventSummaryListSort
import com.adammcneilly.pocketleague.shared.eventsummarylist.domain.GetEventSummariesUseCase
import com.adammcneilly.pocketleague.shared.eventsummarylist.state.EventSummaryListAction
import com.adammcneilly.pocketleague.shared.eventsummarylist.state.eventSummaryListStateMutator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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

    private val eventSummaryRequestFlow = viewState
        .map { viewState ->
            val isUpcoming = when (viewState.currentSort) {
                EventSummaryListSort.UPCOMING -> true
                EventSummaryListSort.PAST -> false
            }

            GetEventSummariesUseCase.Request(
                upcoming = isUpcoming
            )
        }

    init {
        eventSummaryRequestFlow
            .distinctUntilChanged()
            .onEach { request ->
                val fetchAction = EventSummaryListAction.FetchEventSummaries(
                    leagueSlug = "rlcs-2021-22-1",
                    request = request,
                )

                mutator.accept(fetchAction)
            }
            .launchIn(viewModelScope)
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
