package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailsByDateDisplayModel
import com.adammcneilly.pocketleague.core.feature.ScreenState
import com.adammcneilly.pocketleague.core.models.EventStage

/**
 * Displays information about an [EventStage] in user friendly manner.
 */
data class EventStageDetailViewState(
    val eventId: String = "",
    val stageId: String = "",
    val matchesDataState: MatchDetailsByDateDisplayModel? = null
) : ScreenState {

    override val title: String? = null

    val matchesByDateDisplayModel: MatchDetailsByDateDisplayModel?
        get() = matchesDataState?.let {
            val matchesByDate = (1..3).map {
                MatchDetailDisplayModel.placeholder
            }.groupBy(MatchDetailDisplayModel::localDate)

            MatchDetailsByDateDisplayModel(
                matchesByDate = matchesByDate,
                isPlaceholder = true
            )
        }
}
