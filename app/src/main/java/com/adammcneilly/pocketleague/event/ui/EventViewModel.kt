package com.adammcneilly.pocketleague.event.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * A UI State management class for the event detail screen.
 */
@HiltViewModel
class EventViewModel @Inject constructor() : ViewModel() {
    private val _viewState = MutableStateFlow(EventViewState())
    val viewState: StateFlow<EventViewState> = _viewState
}
