package com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.models.CoreStats
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGCoreStats

/**
 * Converts an [OctaneGGCoreStats] entity to a [CoreStats] entity.
 */
fun OctaneGGCoreStats.toCoreStats(): CoreStats {
    return CoreStats(
        shots = this.shots ?: 0,
        goals = this.goals ?: 0,
        saves = this.saves ?: 0,
        assists = this.assists ?: 0,
        score = this.score ?: 0,
        shootingPercentage = this.shootingPercentage ?: 0F,
    )
}
