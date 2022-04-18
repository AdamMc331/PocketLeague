package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName

/**
 * Combination of a [team] and their [stats] for a match or event.
 */
data class OctaneGGTeamStats(
    @SerialName("team")
    val team: OctaneGGTeamOverview? = null,
    @SerialName("stats")
    val stats: OctaneGGStats? = null,
)
