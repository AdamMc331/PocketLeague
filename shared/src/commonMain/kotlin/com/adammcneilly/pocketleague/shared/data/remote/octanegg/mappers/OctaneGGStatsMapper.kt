package com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.models.CoreStats
import com.adammcneilly.pocketleague.core.models.Stats
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGStats

/**
 * Converts an [OctaneGGStats] entity to a [Stats] entity.
 */
fun OctaneGGStats.toStats(): Stats {
    return Stats(
        core = this.core?.toCoreStats() ?: CoreStats(),
    )
}
