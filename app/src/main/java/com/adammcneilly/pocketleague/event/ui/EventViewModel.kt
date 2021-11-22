package com.adammcneilly.pocketleague.event.ui

import android.util.Log
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
            val regional3Rounds = eventService.fetchSwissRounds(
                eventName = "Rocket_League_Championship_Series/2021-22/Fall/North_America/3",
            )

            Log.d("EventViewModel", "Regional 3 Rounds: $regional3Rounds")
        }
    }
}
