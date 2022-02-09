package com.adammcneilly.pocketleague.seriesoverview.domain.models

import com.adammcneilly.pocketleague.models.Team

/**
 * An overview of a series between two teams and how many wins each team had.
 */
data class SeriesOverview(
    val teamOne: Team,
    val teamTwo: Team,
    val teamOneWins: Int,
    val teamTwoWins: Int,
)
