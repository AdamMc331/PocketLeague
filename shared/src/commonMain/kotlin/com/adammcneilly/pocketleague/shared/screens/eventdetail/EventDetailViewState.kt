package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI state of the event detail screen.
 */
data class EventDetailViewState(
    val eventId: String = "",
    val eventDetailState: DataState<EventDetailDisplayModel> = DataState.Loading,
    val participantsState: DataState<List<TeamOverviewDisplayModel>> = DataState.Loading,
) : ScreenState {

    override val title: String? = null

    val eventDetail: EventDetailDisplayModel?
        get() = when (eventDetailState) {
            is DataState.Error -> {
                null
            }
            DataState.Loading -> {
                EventDetailDisplayModel.placeholder
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
                    TeamOverviewDisplayModel.placeholder
                }
            }
            is DataState.Success -> {
                participantsState.data
            }
        }
}
