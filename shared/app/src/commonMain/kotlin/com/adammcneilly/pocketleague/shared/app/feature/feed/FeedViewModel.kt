package com.adammcneilly.pocketleague.shared.app.feature.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.shared.app.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.shared.app.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.EventGroupDisplayModel
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.shared.app.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.shared.app.core.locale.LocaleHelper
import com.adammcneilly.pocketleague.shared.app.domain.usecases.GetOngoingEventsUseCase
import com.adammcneilly.pocketleague.shared.app.domain.usecases.GetPastWeeksMatchesUseCase
import com.adammcneilly.pocketleague.shared.app.domain.usecases.GetUpcomingEventsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FeedViewModel(
    private val dateTimeFormatter: DateTimeFormatter,
    private val getPastWeeksMatchesUseCase: GetPastWeeksMatchesUseCase,
    private val getOngoingEventsUseCase: GetOngoingEventsUseCase,
    private val getUpcomingEventsUseCase: GetUpcomingEventsUseCase,
    private val localeHelper: LocaleHelper,
    private val timeProvider: TimeProvider,
) : ViewModel() {
    private val mutableState = MutableStateFlow(FeedViewState.placeholderState())
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
                    MatchDetailDisplayModel(
                        match = match,
                        dateTimeFormatter = dateTimeFormatter,
                        timeProvider = timeProvider,
                    )
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
        val eventFlow = getOngoingEventsUseCase
            .invoke()
            .map { eventList ->
                eventList.map { event ->
                    EventSummaryDisplayModel(
                        event = event,
                        dateTimeFormatter = dateTimeFormatter,
                        localeHelper = localeHelper,
                    )
                }
            }
            .map(EventGroupDisplayModel.Companion::mapFromEventList)

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
        val eventFlow = getUpcomingEventsUseCase
            .invoke()
            .map { eventList ->
                eventList.map { event ->
                    EventSummaryDisplayModel(
                        event = event,
                        dateTimeFormatter = dateTimeFormatter,
                        localeHelper = localeHelper,
                    )
                }
            }
            .map(EventGroupDisplayModel.Companion::mapFromEventList)

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
}
