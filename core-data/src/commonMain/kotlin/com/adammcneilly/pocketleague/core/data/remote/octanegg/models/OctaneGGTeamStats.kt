package com.adammcneilly.pocketleague.core.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Combination of a [team] and their [stats] for a match or event.
 */
@Serializable
data class OctaneGGTeamStats(
    @SerialName("team")
    val team: OctaneGGTeamOverview? = null,
    @SerialName("stats")
    val stats: OctaneGGStats? = null,
)
