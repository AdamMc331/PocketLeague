package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGStats
import com.adammcneilly.pocketleague.core.models.CoreStats
import com.adammcneilly.pocketleague.core.models.Stats

/**
 * Converts an [OctaneGGStats] entity to a [Stats] entity.
 */
fun OctaneGGStats.toStats(): Stats {
    return Stats(
        core = this.core?.toCoreStats() ?: CoreStats(),
    )
}
