package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.feature.eventdetail.EventDetailParams
import com.adammcneilly.pocketleague.feature.eventdetail.EventDetailScreen
import com.adammcneilly.pocketleague.feature.eventdetail.EventDetailScreenEvent
import com.adammcneilly.pocketleague.feature.eventdetail.EventDetailViewState
import com.adammcneilly.pocketleague.shared.screens.StateManager

object EventDetailScreenBuilder {

    fun build(
        params: EventDetailParams,
        stateManager: StateManager,
    ): EventDetailScreen {
        return EventDetailScreen(
            params = params,
            eventService = stateManager.repository.eventService,
            eventProcessor = stateManager::processEventDetailEvent,
        )
    }
}

private fun StateManager.processEventDetailEvent(
    event: EventDetailScreenEvent,
) {
    when (event) {
        is EventDetailScreenEvent.LoadedEvent -> {
            this.updateScreen(EventDetailViewState::class) { currentState ->
                currentState.copy(
                    eventDetail = event.event,
                )
            }
        }
        is EventDetailScreenEvent.LoadedParticipants -> {
            this.updateScreen(EventDetailViewState::class) { currentState ->
                currentState.copy(
                    participants = event.teams,
                    showLoading = false,
                )
            }
        }
        is EventDetailScreenEvent.Error -> {
            this.updateScreen(EventDetailViewState::class) { currentState ->
                currentState.copy(
                    errorMessage = event.error.message,
                    showLoading = false,
                )
            }
        }
    }
}
