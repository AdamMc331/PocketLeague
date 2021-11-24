package com.adammcneilly.pocketleague.swiss.domain.models

import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview

/**
 * This represents one individual round of a [SwissStage].
 *
 * @param[roundDescription] The description of this round, such as "Round 1 Matches", "Round 2 Low Matches", etc.
 * @param[series] All of the [SeriesOverview] entities for this particular swiss round.
 */
data class SwissRound(
    val roundDescription: String,
    val series: List<SeriesOverview>,
)
