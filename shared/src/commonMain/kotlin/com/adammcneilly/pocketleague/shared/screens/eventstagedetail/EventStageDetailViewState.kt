package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailsByDateDisplayModel
import com.adammcneilly.pocketleague.core.feature.ScreenState
import com.adammcneilly.pocketleague.core.models.EventStage

/**
 * Displays information about an [EventStage] in user friendly manner.
 */
data class EventStageDetailViewState(
    val eventId: String = "",
    val stageId: String = "",
    val matchesByDateDisplayModel: MatchDetailsByDateDisplayModel = MatchDetailsByDateDisplayModel.placeholder,
) : ScreenState {

    override val title: String? = null
}
