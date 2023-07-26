package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.EventRegion

/**
 * Provides a user friendly representation of a [EventRegion].
 */
data class EventRegionDisplayModel(
    val shortName: String,
    val fullName: String,
)

/**
 * Converts a [EventRegion] to a relevant [EventRegionDisplayModel].
 */
fun EventRegion.toDisplayModel(): EventRegionDisplayModel {
    val fullName = when (this) {
        EventRegion.NA -> "North America"
        EventRegion.EU -> "Europe"
        EventRegion.OCE -> "Oceania"
        EventRegion.SAM -> "South America"
        EventRegion.APAC -> "Asia-Pacific"
        EventRegion.MENA -> "Middle East & North Africa"
        EventRegion.SSA -> "Sub Saharan Africa"
        EventRegion.INT -> "International"
        EventRegion.Unknown -> "Unknown"
    }

    return EventRegionDisplayModel(
        shortName = this.name,
        fullName = fullName,
    )
}
