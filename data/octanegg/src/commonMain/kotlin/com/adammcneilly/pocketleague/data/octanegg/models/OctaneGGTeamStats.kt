package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.CoreStats
import com.adammcneilly.pocketleague.core.models.Stats
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
    val stats: OctaneGGStats? = null
)

/**
 * Converts an [OctaneGGStats] entity to a [Stats] entity.
 */
fun OctaneGGStats.toStats(): Stats {
    return Stats(
        core = this.core?.toCoreStats() ?: CoreStats()
    )
}
