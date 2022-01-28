package com.adammcneilly.pocketleague.fakes

import com.adammcneilly.pocketleague.seriesoverview.ui.SeriesOverviewDisplayModel

val fakeSeriesOverviewDisplayModel = SeriesOverviewDisplayModel(
    teamOne = fakeTeamOverviewDisplayModel,
    teamTwo = fakeTeamOverviewDisplayModel,
    teamOneWins = 0,
    teamTwoWins = 0,
)
