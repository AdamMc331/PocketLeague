package com.adammcneilly.pocketleague.eventsummary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.eventsummary.domain.usecases.GetUpcomingEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventSummaryListViewModel @Inject constructor(
    private val getUpcomingEventsUseCase: GetUpcomingEventsUseCase,
) : ViewModel() {

    private val _viewState: MutableStateFlow<EventSummaryListViewState> =
        MutableStateFlow(EventSummaryListViewState.Loading)
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getUpcomingEventsUseCase()

            _viewState.value = when (result) {
                is Result.Success -> {
                    EventSummaryListViewState.Success(
                        events = result.data,
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
