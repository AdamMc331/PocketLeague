package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.models.EventTier

/**
 * Displays information about an [EventTier] in a user friendly fashion.
 */
data class EventTierDisplayModel(
    val name: String,
    val description: String,
) {
    constructor(tier: EventTier) : this(
        name = "${tier.name}-Tier",
        description = tier.description(),
    )
}

private fun EventTier.description(): String {
    return when (this) {
        EventTier.S -> {
            "S-Tier events include only the world's elite teams."
        }

        EventTier.A -> {
            "A-Tier events include top competition, but often have open qualifiers."
        }

        EventTier.B -> {
            "B-Tier events have moderate impact and prize pools with strong competition."
        }

        EventTier.C -> {
            "C-Tier events focus on a bubble scene and have small prize pools."
        }

        EventTier.D -> {
            "D-Tier events have small prize pools and are outside the coverage of most RLCS events."
        }

        EventTier.Unknown -> {
            "The Tier of this event is unreported."
        }
    }
}
