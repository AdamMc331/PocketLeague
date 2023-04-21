package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.EventRegion

/**
 * Provides a user friendly representation of a [EventRegion].
 */
data class EventRegionDisplayModel(
    val name: String,
    val description: String,
)

/**
 * Converts a [EventRegion] to a relevant [EventRegionDisplayModel].
 */
fun EventRegion.toDisplayModel(): EventRegionDisplayModel {
    val name = "Region: ${this.name}"

    val description = when (this) {
        EventRegion.NA -> {
            "This event takes place in North America."
        }
        EventRegion.EU -> {
            "This event takes place in Europe."
        }
        EventRegion.OCE -> {
            "This event takes place in Oceania."
        }
        EventRegion.SAM -> {
            "This event takes place in South America."
        }
        EventRegion.ASIA -> {
            "Ths event takes place in Asia."
        }
        EventRegion.ME -> {
            "This event takes place in the Middle East."
        }
        EventRegion.INT -> {
            "This event takes place internationally."
        }
        EventRegion.Unknown -> {
            "The region of this event is unreported."
        }
        EventRegion.SSA -> {
            "This event takes place in Sub-Saharan Africa."
        }
    }

    return EventRegionDisplayModel(
        name = name,
        description = description,
    )
}
