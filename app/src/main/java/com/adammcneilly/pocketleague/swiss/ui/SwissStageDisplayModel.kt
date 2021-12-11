package com.adammcneilly.pocketleague.swiss.ui

import com.adammcneilly.pocketleague.swiss.domain.models.SwissRound
import com.adammcneilly.pocketleague.swiss.domain.models.SwissStage

/**
 * Takes a list of [rounds] and presents them in a user friendly manner.
 */
data class SwissStageDisplayModel(
    val rounds: List<SwissRoundDisplayModel>,
)

/**
 * Converts a [SwissStage] to a [SwissStageDisplayModel] which will be used to render information
 * on the UI.
 */
fun SwissStage.toDisplayModel(): SwissStageDisplayModel {
    return SwissStageDisplayModel(
        rounds = this.rounds.map(SwissRound::toDisplayModel),
    )
}
