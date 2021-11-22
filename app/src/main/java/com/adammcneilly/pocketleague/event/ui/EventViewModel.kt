package com.adammcneilly.pocketleague.event.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.event.data.EventService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val eventService: EventService,
) : ViewModel() {

    init {
        viewModelScope.launch {
            eventService.fetchNARegional3()
        }
    }
}
