package com.adammcneilly.pocketleague.swiss.ui

import com.adammcneilly.pocketleague.swiss.domain.models.SwissRound
import com.adammcneilly.pocketleague.swiss.domain.models.SwissStage

data class SwissStageDisplayModel(
    val rounds: List<SwissRoundDisplayModel>,
)

fun SwissStage.toDisplayModel(): SwissStageDisplayModel {
    return SwissStageDisplayModel(
        rounds = this.rounds.map(SwissRound::toDisplayModel),
    )
}
