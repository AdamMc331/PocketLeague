package com.adammcneilly.pocketleague.seriesdetail.ui

import com.adammcneilly.pocketleague.gameresult.ui.GameResultDisplayModel
import com.adammcneilly.pocketleague.teamdetail.ui.TeamDetailDisplayModel

data class SeriesDetailDisplayModel(
    val gameTime: String,
    val games: List<GameResultDisplayModel>,
    val teamOne: TeamDetailDisplayModel,
    val teamTwo: TeamDetailDisplayModel,
) {

    private val teamOneWins = games.count { game ->
        game.teamOneWinner
    }

    private val teamTwoWins = games.count { game ->
        game.teamTwoWinner
    }

    val teamOneWinner: Boolean
        get() = teamOneWins > teamTwoWins

    val teamTwoWinner: Boolean
        get() = teamTwoWins > teamOneWins
}
