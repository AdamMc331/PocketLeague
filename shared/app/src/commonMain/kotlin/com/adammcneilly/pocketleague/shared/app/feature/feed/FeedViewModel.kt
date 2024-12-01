package com.adammcneilly.pocketleague.shared.app.feature.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.shared.app.domain.usecases.GetOngoingEventsUseCase
import com.adammcneilly.pocketleague.shared.app.domain.usecases.GetPastWeeksMatchesUseCase
import com.adammcneilly.pocketleague.shared.app.domain.usecases.GetUpcomingEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(
    private val getPastWeeksMatchesUseCase: GetPastWeeksMatchesUseCase,
    private val getOngoingEventsUseCase: GetOngoingEventsUseCase,
    private val getUpcomingEventsUseCase: GetUpcomingEventsUseCase,
) : ViewModel() {
    private val mutableState = MutableStateFlow(FeedViewState.placeholderState())
    val state = mutableState.asStateFlow()

    init {
        observePastWeeksMatches()
        observeOngoingEvents()
        observeUpcomingEvents()
    }

    private fun observePastWeeksMatches() {
        viewModelScope.launch {
            getPastWeeksMatchesUseCase
                .invoke()
                .collect { matchList ->
                    mutableState.update { currentState ->
                        currentState.copy(
                            recentMatches = matchList,
                        )
                    }
                }
        }
    }

    private fun observeOngoingEvents() {
        viewModelScope.launch {
            getOngoingEventsUseCase
                .invoke()
                .collect { eventList ->
                    mutableState.update { currentState ->
                        currentState.copy(
                            ongoingEvents = eventList,
                        )
                    }
                }
        }
    }

    private fun observeUpcomingEvents() {
        viewModelScope.launch {
            getUpcomingEventsUseCase
                .invoke()
                .collect { eventList ->
                    mutableState.update { currentState ->
                        currentState.copy(
                            ongoingEvents = eventList,
                        )
                    }
                }
        }
    }
}
