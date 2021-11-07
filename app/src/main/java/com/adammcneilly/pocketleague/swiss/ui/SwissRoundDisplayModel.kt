package com.adammcneilly.pocketleague.swiss.ui

import com.adammcneilly.pocketleague.seriesoverview.ui.SeriesOverviewDisplayModel

data class SwissRoundDisplayModel(
    val roundDescription: String,
    val series: List<SeriesOverviewDisplayModel>,
)
