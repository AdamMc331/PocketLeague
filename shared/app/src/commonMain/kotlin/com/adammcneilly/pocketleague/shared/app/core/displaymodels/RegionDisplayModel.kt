package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.models.Region

/**
 * Provides a user friendly representation of a [Region].
 */
data class RegionDisplayModel(
    val name: String,
) {
    /**
     * In a perfect world, we would have a when statement here to convert each region
     * into a localized string, but for now we can just leverage
     * the fact that the liquipediaRegionKey is a user friendly representation of the region's name.
     */
    constructor(region: Region) : this(
        name = region.liquipediaRegionKey,
    )
}
