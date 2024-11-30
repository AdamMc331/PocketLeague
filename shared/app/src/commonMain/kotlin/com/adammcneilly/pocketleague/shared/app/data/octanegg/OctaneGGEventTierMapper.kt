package com.adammcneilly.pocketleague.shared.app.data.octanegg

import com.adammcneilly.pocketleague.shared.app.core.models.EventTier

object OctaneGGEventTierMapper {
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
