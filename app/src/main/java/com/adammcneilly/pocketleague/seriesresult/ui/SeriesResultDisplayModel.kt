package com.adammcneilly.pocketleague.seriesresult.ui

import com.adammcneilly.pocketleague.gameresult.ui.GameResultDisplayModel
import com.adammcneilly.pocketleague.teamdetail.ui.TeamDetailDisplayModel

data class SeriesResultDisplayModel(
    val gameTime: String,
    val games: List<GameResultDisplayModel>,
    val teamOne: TeamDetailDisplayModel,
    val teamTwo: TeamDetailDisplayModel,
)
