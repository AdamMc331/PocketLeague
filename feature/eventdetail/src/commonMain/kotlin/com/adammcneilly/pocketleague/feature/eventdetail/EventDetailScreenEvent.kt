package com.adammcneilly.pocketleague.feature.eventdetail

import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel

sealed class EventDetailScreenEvent {
    data class LoadedEvent(
        val event: EventDetailDisplayModel,
    ) : EventDetailScreenEvent()

    data class LoadedParticipants(
        val teams: List<TeamOverviewDisplayModel>,
    ) : EventDetailScreenEvent()

    data class Error(
        val error: Throwable,
    ) : EventDetailScreenEvent()
}
