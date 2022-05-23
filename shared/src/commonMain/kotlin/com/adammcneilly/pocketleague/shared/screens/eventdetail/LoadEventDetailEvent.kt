package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.shared.screens.Events
import kotlinx.coroutines.flow.collect

/**
 * Load detailed information about a given [eventId].
 */
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
