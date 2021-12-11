package com.adammcneilly.pocketleague.swiss.ui

import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview
import com.adammcneilly.pocketleague.seriesoverview.ui.SeriesOverviewDisplayModel
import com.adammcneilly.pocketleague.seriesoverview.ui.toDisplayModel
import com.adammcneilly.pocketleague.swiss.domain.models.SwissRound

/**
 * Defines UI friendly information of a [SwissRound] entity.
 */
data class SwissRoundDisplayModel(
    val roundDescription: String,
    val series: List<SeriesOverviewDisplayModel>,
)

/**
 * Given a [SwissRound] domain item, convert it to a corresponding [SwissRoundDisplayModel] for usage
 * in UI code.
 */
fun SwissRound.toDisplayModel(): SwissRoundDisplayModel {
    return SwissRoundDisplayModel(
        roundDescription = this.roundDescription,
        series = this.series.map(SeriesOverview::toDisplayModel),
    )
}
