package com.adammcneilly.pocketleague.shared.app.data.octanegg.dto

import com.adammcneilly.pocketleague.shared.app.core.models.CoreStats
import com.adammcneilly.pocketleague.shared.app.core.models.Stats
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
    val positioning: OctaneGGPositioningStats? = null,
) {
    /**
     * Converts an [OctaneGGStats] entity to a [Stats] entity.
     */
    fun toStats(): Stats {
        return Stats(
            core = this.core?.toCoreStats() ?: CoreStats(),
        )
    }
}
