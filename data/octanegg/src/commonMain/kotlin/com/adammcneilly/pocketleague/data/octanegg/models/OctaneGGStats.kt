package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Stats
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Tracks any number of statistics relevant to a player or team.
 */
@Serializable
data class OctaneGGStats(
    @SerialName("boost")
    val boost: OctaneGGBoostStats? = null,
    @SerialName("core")
    val core: OctaneGGCoreStats? = null,
    @SerialName("demo")
    val demo: OctaneGGDemoStats? = null,
    @SerialName("movement")
    val movement: OctaneGGMovementStats? = null,
    @SerialName("positioning")
    val positioning: OctaneGGPositioningStats? = null
)

/**
 * Converts an [OctaneGGStats] entity to a [Stats] entity in our domain.
 */
fun OctaneGGStats.toStats(): Stats {
    requireNotNull(core)

    return Stats(
        core = this.core.toCoreStats()
    )
}
