package com.adammcneilly.pocketleague.swiss.domain.models

import com.adammcneilly.pocketleague.seriesoverview.domain.models.SeriesOverview

/**
 * This represents one individual round of a [SwissStage].
 *
 * @property[roundDescription] The description of this round, such as "Round 1 Matches", "Round 2 Low Matches", etc.
 * @property[series] All of the [SeriesOverview] entities for this particular swiss round.
 */
data class SwissRound(
    val roundDescription: String,
    val series: List<SeriesOverview>,
)
