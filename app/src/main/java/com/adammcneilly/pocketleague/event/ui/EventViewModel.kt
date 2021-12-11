package com.adammcneilly.pocketleague.event.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.event.data.EventService
import com.adammcneilly.pocketleague.swiss.ui.toDisplayModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A UI State management class for the event detail screen.
 */
@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventService: EventService,
) : ViewModel() {
    private val _viewState = MutableStateFlow(EventViewState())
    val viewState: StateFlow<EventViewState> = _viewState

    init {
        viewModelScope.launch {
            val regional3Rounds = eventService.fetchSwissStage(
                eventName = "Rocket_League_Championship_Series/2021-22/Fall/North_America/3",
            )

            _viewState.value = when (regional3Rounds) {
                is Result.Success -> {
                    _viewState.value.copy(
                        showLoading = false,
                        showContent = true,
                        swissStage = regional3Rounds.data.toDisplayModel()
                    )
                }
                is Result.Error -> {
                    _viewState.value.copy(
                        showLoading = false,
                        showError = true,
                    )
                }
            }
        }
    }
}
