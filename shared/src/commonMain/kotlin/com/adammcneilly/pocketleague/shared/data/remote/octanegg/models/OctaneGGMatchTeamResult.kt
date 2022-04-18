package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName

/**
 * Shows the result of a specific [team] within a match.
 *
 * @property[score] The score for this [team] during the match.
 * @property[winner] If true, this [team] is the winner of the match.
 * @property[team] Information about the team and their stats.
 */
data class OctaneGGMatchTeamResult(
    @SerialName("score")
    val score: Int? = null,
    @SerialName("winner")
    val winner: Boolean? = null,
    @SerialName("team")
    val team: OctaneGGTeamStats? = null,
)
