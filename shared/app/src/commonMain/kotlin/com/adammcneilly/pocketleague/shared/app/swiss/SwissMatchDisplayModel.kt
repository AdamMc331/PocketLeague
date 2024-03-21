package com.adammcneilly.pocketleague.shared.app.swiss

import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel

data class SwissMatchDisplayModel(
    val roundName: String,
    val match: MatchDetailDisplayModel,
    val games: List<GameDetailDisplayModel>,
)
