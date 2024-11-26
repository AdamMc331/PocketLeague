package com.adammcneilly.pocketleague.shared.app.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toSummaryDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.data.event.api.EventListRequest
import com.adammcneilly.pocketleague.data.event.api.EventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(
    private val getPastWeeksMatchesUseCase: GetPastWeeksMatchesUseCase,
    private val eventRepository: EventRepository,
    private val timeProvider: TimeProvider,
) : ViewModel() {
    private val mutableState = MutableStateFlow(FeedUiState.placeholderState())
    val state = mutableState.asStateFlow()

    init {
        observePastWeeksMatches()
        observeOngoingEvents()
        observeUpcomingEvents()
    }

    private fun observePastWeeksMatches() {
        val matchFlow = getPastWeeksMatchesUseCase
            .invoke()
            .map { matchList ->
                matchList.map { match ->
                    match.toDetailDisplayModel(timeProvider)
                }
            }

        viewModelScope.launch {
            matchFlow.collect { matchList ->
                mutableState.update { currentState ->
                    currentState.copy(
                        recentMatches = matchList,
                    )
                }
            }
        }
    }

    private fun observeOngoingEvents() {
        val request = EventListRequest.OnDate(
            dateUtc = timeProvider.now(),
        )

        val eventFlow = eventListRequestFlow(request)

        viewModelScope.launch {
            eventFlow.collect { eventList ->
                mutableState.update { currentState ->
                    currentState.copy(
                        ongoingEvents = eventList,
                    )
                }
            }
        }
    }

    private fun observeUpcomingEvents() {
        val request = EventListRequest.AfterDate(
            dateUtc = timeProvider.now(),
        )

        val eventFlow = eventListRequestFlow(request)

        viewModelScope.launch {
            eventFlow.collect { eventList ->
                mutableState.update { currentState ->
                    currentState.copy(
                        ongoingEvents = eventList,
                    )
                }
            }
        }
    }

    private fun eventListRequestFlow(
        request: EventListRequest,
    ): Flow<List<EventGroupDisplayModel>> {
        return eventRepository
            .stream(request)
            .map { eventList ->
                eventList.map(Event::toSummaryDisplayModel)
            }
            .map(EventGroupDisplayModel.Companion::mapFromEventList)
    }
}
