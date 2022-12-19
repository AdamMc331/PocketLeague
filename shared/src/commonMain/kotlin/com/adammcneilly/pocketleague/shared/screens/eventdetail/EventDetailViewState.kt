package com.adammcneilly.pocketleague.shared.screens.eventdetail

import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Defines the UI state of the event detail screen.
 */
data class EventDetailViewState(
    val eventId: String = "",
    val eventDetail: EventDetailDisplayModel? = null,
    val participants: List<TeamOverviewDisplayModel> = emptyList(),
) : ScreenState {

    override val title: String? = null
}
