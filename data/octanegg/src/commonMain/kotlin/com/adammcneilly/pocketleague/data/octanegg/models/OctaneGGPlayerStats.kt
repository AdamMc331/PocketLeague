package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.GamePlayerResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Contains the core [stats] and [advanced] stats for a [player].
 */
@Serializable
data class OctaneGGPlayerStats(
    @SerialName("advanced")
    val advanced: OctaneGGAdvancedStats? = null,
    @SerialName("player")
    val player: OctaneGGPlayer? = null,
    @SerialName("stats")
    val stats: OctaneGGStats? = null
)

/**
 * Maps an [OctaneGGPlayerStats] entity, which should represent a player within a game,
 * to a [GamePlayerResult] entity.
 *
 * It is up to the caller to make sure this is used in the right domain, as [OctaneGGPlayerStats]
 * can represent a player and their stats in any situation.
 */
fun OctaneGGPlayerStats.toGamePlayerResult(): GamePlayerResult {
    requireNotNull(this.player)
    requireNotNull(this.stats)

    return GamePlayerResult(
        player = this.player.toPlayer(),
        stats = this.stats.toStats(),
    )
}
