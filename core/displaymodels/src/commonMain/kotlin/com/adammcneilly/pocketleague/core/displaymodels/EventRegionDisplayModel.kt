package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.EventRegion

/**
 * Provides a user friendly representation of a [EventRegion].
 */
data class EventRegionDisplayModel(
    val name: String,
)

/**
 * Converts a [EventRegion] to a relevant [EventRegionDisplayModel].
 */
fun EventRegion.toDisplayModel(): EventRegionDisplayModel {
    // In a perfect world, we would have a when statement here to convert each region
    // into a localized string, but for now we can just leverage
    // the fact that the liquipediaRegionKey is a user friendly representation of the region's name.
    val name = this.liquipediaRegionKey

    return EventRegionDisplayModel(
        name = name,
    )
}
