package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI state of the event detail screen.
 *
 * @property[eventId] The unique identifier of the event we are showing detailed information for.
 * @property[showLoading] True if we should show some loading indicator on the screen, false otherwise.
 * @property[eventDetail] If available, user friendly information about the event.
 * @property[errorMessage] A user friendly description of what went wrong, if we had an issue displaying
 * this event.
 * @property[participants] The different teams participating within an event. This is typically only
 * for main event, does not include qualifiers.
 */
data class EventDetailViewState(
    val eventId: String = "",
    val eventDetailState: DataState<EventDetailDisplayModel> = DataState.Loading,
    val participantsState: DataState<List<TeamOverviewDisplayModel>> = DataState.Loading,
) : ScreenState {

    val eventDetail: EventDetailDisplayModel?
        get() = when (eventDetailState) {
            is DataState.Error -> {
                null
            }
            DataState.Loading -> {
                EventDetailDisplayModel(
                    isPlaceholder = true,
                )
            }
            is DataState.Success -> {
                eventDetailState.data
            }
        }

    val participants: List<TeamOverviewDisplayModel>?
        get() = when (participantsState) {
            is DataState.Error -> {
                null
            }
            DataState.Loading -> {
                (1..3).map {
                    TeamOverviewDisplayModel(
                        isPlaceholder = true,
                    )
                }
            }
            is DataState.Success -> {
                participantsState.data
            }
        }
}
