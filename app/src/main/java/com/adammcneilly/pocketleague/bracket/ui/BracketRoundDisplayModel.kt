package com.adammcneilly.pocketleague.bracket.ui

import com.adammcneilly.pocketleague.seriesoverview.ui.SeriesOverviewDisplayModel

/**
 * Information about a round in a bracket format, specified by the [roundName] like Quarter-Final or Semi-Final.
 */
data class BracketRoundDisplayModel(
    val roundName: String,
    val series: List<SeriesOverviewDisplayModel>,
)
