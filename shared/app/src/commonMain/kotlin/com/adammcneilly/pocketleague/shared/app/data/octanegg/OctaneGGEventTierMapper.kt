package com.adammcneilly.pocketleague.shared.app.data.octanegg

import com.adammcneilly.pocketleague.shared.app.core.models.EventTier

object OctaneGGEventTierMapper {
    /**
     * Converts a tier string from the Octane.gg api to an [EventTier] enum entry.
     */
    fun fromString(
        tier: String,
    ): EventTier {
        return when (tier) {
            "S" -> EventTier.S
            "A" -> EventTier.A
            "B" -> EventTier.B
            "C" -> EventTier.C
            "D" -> EventTier.D
            else -> EventTier.Unknown
        }
    }
}
