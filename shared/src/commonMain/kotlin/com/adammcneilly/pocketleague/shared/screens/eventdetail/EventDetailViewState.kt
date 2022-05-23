package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI state of the event detail screen.
 *
 * @property[showLoading] True if we should show some loading indicator on the screen, false otherwise.
 * @property[eventId] The unique identifier of the event we are showing detailed information for.
 * @property[event] Detailed information about an [Event] once it has been requested.
 */
data class EventDetailViewState(
    val eventId: String = "",
    val eventDetailState: DataState<EventDetailDisplayModel> = DataState.Loading,
) : ScreenState {

    val eventDetail: EventDetailDisplayModel
        get() = when (eventDetailState) {
            is DataState.Loading -> {
                EventDetailDisplayModel()
            }
            is DataState.Success -> {
                eventDetailState.data
            }
            is DataState.Error -> {
                EventDetailDisplayModel()
            }
        }
}
