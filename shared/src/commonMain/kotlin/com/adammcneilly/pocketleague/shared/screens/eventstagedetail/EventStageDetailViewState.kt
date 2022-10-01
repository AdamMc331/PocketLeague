package com.adammcneilly.pocketleague.shared.screens.eventstagedetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailsByDateDisplayModel
import com.adammcneilly.pocketleague.core.models.EventStage
import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * Displays information about an [EventStage] in user friendly manner.
 */
data class EventStageDetailViewState(
    val eventId: String = "",
    val stageId: String = "",
    val matchesDataState: DataState<MatchDetailsByDateDisplayModel> = DataState.Loading,
) : ScreenState {

    val matchesByDateDisplayModel: MatchDetailsByDateDisplayModel?
        get() = when (matchesDataState) {
            is DataState.Error -> {
                null
            }
            DataState.Loading -> {
                val matchesByDate = (1..3).map {
                    MatchDetailDisplayModel(
                        isPlaceholder = true,
                    )
                }.groupBy(MatchDetailDisplayModel::localDate)

                MatchDetailsByDateDisplayModel(
                    matchesByDate = matchesByDate,
                    isPlaceholder = true,
                )
            }
            is DataState.Success -> {
                matchesDataState.data
            }
        }
}
