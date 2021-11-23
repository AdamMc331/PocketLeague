package com.adammcneilly.pocketleague.seriesoverview.domain.models

import com.adammcneilly.pocketleague.core.domain.models.Team

data class SeriesOverview(
    val teamOne: Team,
    val teamTwo: Team,
    val teamOneWins: Int,
    val teamTwoWins: Int,
)
