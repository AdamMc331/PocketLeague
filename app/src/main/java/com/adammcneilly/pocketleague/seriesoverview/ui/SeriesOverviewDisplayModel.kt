package com.adammcneilly.pocketleague.seriesoverview.ui

import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview
import com.adammcneilly.pocketleague.teamoverview.ui.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.teamoverview.ui.toOverviewDisplayModelWithoutRoster

/**
 * Presents a [SeriesOverview] entity on the UI with user friendly values.
 */
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

/**
 * Converts a [SeriesOverview] to a [SeriesOverviewDisplayModel] so it can be rendered appropriately
 * on the UI.
 */
fun SeriesOverview.toDisplayModel(): SeriesOverviewDisplayModel {
    return SeriesOverviewDisplayModel(
        teamOne = this.teamOne.toOverviewDisplayModelWithoutRoster(),
        teamTwo = this.teamTwo.toOverviewDisplayModelWithoutRoster(),
        teamOneWins = this.teamOneWins,
        teamTwoWins = this.teamTwoWins,
    )
}
