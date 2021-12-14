package com.adammcneilly.pocketleague.event.ui

import android.util.Log
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
            val events = eventService.fetchAllEvents(leagueSlug = "rlcs-2021-22-1")

            Log.d("Test", events.toString())

            val regional3Rounds = eventService.fetchSwissStage(
                eventName = "rlcs-2021-22-season-fall-split-regional-3-north-america",
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
