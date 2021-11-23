package com.adammcneilly.pocketleague.swiss.ui

import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview
import com.adammcneilly.pocketleague.seriesoverview.ui.SeriesOverviewDisplayModel
import com.adammcneilly.pocketleague.seriesoverview.ui.toDisplayModel
import com.adammcneilly.pocketleague.swiss.domain.models.SwissRound

data class SwissRoundDisplayModel(
    val roundDescription: String,
    val series: List<SeriesOverviewDisplayModel>,
)

fun SwissRound.toDisplayModel(): SwissRoundDisplayModel {
    return SwissRoundDisplayModel(
        roundDescription = this.roundDescription,
        series = this.series.map(SeriesOverview::toDisplayModel),
    )
}
