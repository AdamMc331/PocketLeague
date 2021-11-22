package com.adammcneilly.pocketleague.swiss.ui

import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview

data class SwissRound(
    val roundDescription: String,
    val series: List<SeriesOverview>,
)
