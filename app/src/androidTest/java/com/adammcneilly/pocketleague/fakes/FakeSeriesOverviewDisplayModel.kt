package com.adammcneilly.pocketleague.fakes

import com.adammcneilly.pocketleague.seriesoverview.ui.SeriesOverviewDisplayModel

val fakeSeriesOverviewDisplayModel = SeriesOverviewDisplayModel(
    teamOne = fakeTeamOverviewDisplayModel.copy(
        name = "Team One",
    ),
    teamTwo = fakeTeamOverviewDisplayModel.copy(
        name = "Team Two",
    ),
    teamOneWins = 0,
    teamTwoWins = 0,
)
