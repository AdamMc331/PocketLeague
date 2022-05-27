package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGPlayerStats
import com.adammcneilly.pocketleague.core.models.GamePlayerResult

/**
 * Maps an [OctaneGGPlayerStats] entity, which should represent a player within a game,
 * to a [GamePlayerResult] entity.
 *
 * It is up to the caller to make sure this is used in the right domain, as [OctaneGGPlayerStats]
 * can represent a player and their stats in any situation.
 */
fun OctaneGGPlayerStats.toGamePlayerResult(): GamePlayerResult {
    return GamePlayerResult(
        player = this.player?.toPlayer()!!,
        stats = this.stats?.toStats()!!,
    )
}
