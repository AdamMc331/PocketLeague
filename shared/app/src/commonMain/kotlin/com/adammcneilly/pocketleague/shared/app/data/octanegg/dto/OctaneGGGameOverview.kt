package com.adammcneilly.pocketleague.shared.app.data.octanegg.dto

import com.adammcneilly.pocketleague.shared.app.core.models.GameOverview
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Provided by the octane.gg api to get overview
 * information on a game.
 */
@Serializable
data class OctaneGGGameOverview(
    @SerialName("_id")
    val id: String? = null,
    @SerialName("blue")
    val blueScore: Int? = null,
    @SerialName("orange")
    val orangeScore: Int? = null,
    @SerialName("duration")
    val durationSeconds: Int? = null,
) {
    /**
     * Convert a game overview from octane domain to ours.
     */
    fun toGameOverview(): GameOverview {
        return GameOverview(
            id = this.id.orEmpty(),
            blueScore = this.blueScore ?: 0,
            orangeScore = this.orangeScore ?: 0,
            durationSeconds = this.durationSeconds ?: 0,
        )
    }
}
