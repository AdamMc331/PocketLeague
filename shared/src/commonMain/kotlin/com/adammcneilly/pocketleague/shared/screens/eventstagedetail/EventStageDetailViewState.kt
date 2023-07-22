package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailsByDateDisplayModel
import com.adammcneilly.pocketleague.core.feature.ScreenState
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage

/**
 * Displays information about an [EventStage] in user friendly manner.
 */
data class EventStageDetailViewState(
    val eventId: Event.Id = Event.Id(""),
    val stageId: EventStage.Id = EventStage.Id(""),
    val matchesByDateDisplayModel: MatchDetailsByDateDisplayModel = MatchDetailsByDateDisplayModel.placeholder,
    val allMatches: List<MatchDetailDisplayModel> = emptyList(),
) : ScreenState {

    override val title: String? = null
}
