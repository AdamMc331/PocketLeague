package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Displays information about an [EventStage] in user friendly manner.
 */
data class EventStageDetailViewState(
    val eventId: String = "",
    val stageId: String = "",
    val showLoading: Boolean = true,
    val errorMessage: String? = null,
    val matchesByDate: Map<String, List<MatchDetailDisplayModel>> = emptyMap(),
) : ScreenState
