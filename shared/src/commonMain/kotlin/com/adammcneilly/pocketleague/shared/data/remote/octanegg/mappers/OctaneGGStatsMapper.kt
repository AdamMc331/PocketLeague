package com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGStats
import com.adammcneilly.pocketleague.shared.models.CoreStats
import com.adammcneilly.pocketleague.shared.models.Stats

/**
 * Converts an [OctaneGGStats] entity to a [Stats] entity.
 */
fun OctaneGGStats.toStats(): Stats {
    return Stats(
        core = this.core?.toCoreStats() ?: CoreStats(),
    )
}
