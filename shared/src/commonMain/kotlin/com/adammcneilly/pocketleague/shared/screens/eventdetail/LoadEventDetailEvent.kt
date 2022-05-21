package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.shared.data.DataState
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect

fun Events.loadEventDetail(
    eventId: String,
) = screenCoroutine {
    repository.eventRepository.fetchEvent(
        eventId,
    ).collect { repoResult ->
        stateManager.updateScreen(EventDetailViewState::class) {
            val viewState = when (repoResult) {
                is DataState.Loading -> {
                    it.copy(
                        showLoading = true,
                    )
                }
                is DataState.Success -> {
                    it.copy(
                        event = repoResult.data,
                    )
                }
                is DataState.Error -> {
                    it
                }
            }

            viewState
        }
    }
}
