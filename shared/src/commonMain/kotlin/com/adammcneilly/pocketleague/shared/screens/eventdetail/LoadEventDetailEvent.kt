package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
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
            val eventDetailState: DataState<EventDetailDisplayModel> = when (repoResult) {
                is DataState.Loading -> {
                    DataState.Loading
                }
                is DataState.Success -> {
                    DataState.Success(repoResult.data.toDetailDisplayModel())
                }
                is DataState.Error -> {
                    DataState.Error(repoResult.error)
                }
            }

            it.copy(
                eventDetailState = eventDetailState,
            )
        }
    }
}
