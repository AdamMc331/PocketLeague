package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.EventTier

/**
 * Displays information about an [EventTier] in a user friendly fashion.
 */
data class EventTierDisplayModel(
    val name: String,
    val description: String
)

/**
 * Converts an [EventTier] into a relevant [EventTierDisplayModel].
 */
fun EventTier.toDisplayModel(): EventTierDisplayModel {
    val name = "${this.name}-Tier"

    val description = when (this) {
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

    return EventTierDisplayModel(
        name = name,
        description = description
    )
}
