package com.adammcneilly.pocketleague.eventsummary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.eventsummary.domain.usecases.FetchUpcomingEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A state management container for the [EventSummaryListScreen].
 */
@HiltViewModel
class EventSummaryListViewModel @Inject constructor(
    private val fetchUpcomingEventsUseCase: FetchUpcomingEventsUseCase,
    private val dateTimeHelper: DateTimeHelper,
) : ViewModel() {

    private val _viewState: MutableStateFlow<EventSummaryListViewState> =
        MutableStateFlow(EventSummaryListViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = fetchUpcomingEventsUseCase()

            _viewState.value = when (result) {
                is Result.Success -> {
                    EventSummaryListViewState.Success(
                        events = result.data.map { event ->
                            EventSummaryDisplayModel(
                                startDate = dateTimeHelper.getEventDayString(event.startDate),
                                tournamentName = event.tournamentName,
                                eventName = event.eventName,
                            )
                        },
                    )
                }
                is Result.Error -> {
                    EventSummaryListViewState.Error(
                        errorMessage = UIText.StringText("Fetching upcoming events failed."),
                    )
                }
            }
        }
    }
}
