package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect

/**
 * Load detailed information about a given [eventId].
 */
fun Events.loadEventDetail(
    eventId: String,
) = screenCoroutine {
    fetchEventDetail(eventId)

    fetchEventParticipants(eventId)
}

private suspend fun Events.fetchEventParticipants(
    eventId: String,
) {
    repository.eventRepository.fetchEventParticipants(
        eventId,
    ).collect { repoResult ->
        stateManager.updateScreen(EventDetailViewState::class) {
            val viewState = when (repoResult) {
                is DataState.Loading -> {
                    // Show team loading?
                    it
                }
                is DataState.Success -> {
                    it.copy(
                        participants = repoResult.data.map {
                            it.toOverviewDisplayModel()
                        },
                    )
                }
                is DataState.Error -> {
                    // Show team error?
                    it
                }
            }

            viewState
        }
    }
}

private suspend fun Events.fetchEventDetail(eventId: String) {
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
                        showLoading = false,
                        eventDetail = repoResult.data.toDetailDisplayModel()
                    )
                }
                is DataState.Error -> {
                    it.copy(
                        showLoading = false,
                        errorMessage = repoResult.error.message,
                    )
                }
            }

            viewState
        }
    }
}
