package com.adammcneilly.pocketleague.seriesoverview.ui

import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewDisplayModel

data class SeriesOverviewDisplayModel(
    val teamOne: TeamOverviewDisplayModel,
    val teamTwo: TeamOverviewDisplayModel,
    val teamOneWins: Int,
    val teamTwoWins: Int,
) {

    val teamOneWinner: Boolean
        get() = teamOneWins > teamTwoWins

    val teamTwoWinner: Boolean
        get() = teamTwoWins > teamOneWins
}
