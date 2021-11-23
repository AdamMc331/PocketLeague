package com.adammcneilly.pocketleague.seriesoverview.ui

import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview
import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.teamoverview.ui.toOverviewDisplayModelWithoutRoster

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

fun SeriesOverview.toDisplayModel(): SeriesOverviewDisplayModel {
    return SeriesOverviewDisplayModel(
        teamOne = this.teamOne.toOverviewDisplayModelWithoutRoster(),
        teamTwo = this.teamTwo.toOverviewDisplayModelWithoutRoster(),
        teamOneWins = this.teamOneWins,
        teamTwoWins = this.teamTwoWins,
    )
}
