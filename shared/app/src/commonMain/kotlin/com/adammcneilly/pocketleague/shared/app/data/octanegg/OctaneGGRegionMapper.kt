package com.adammcneilly.pocketleague.shared.app.data.octanegg

import com.adammcneilly.pocketleague.shared.app.core.models.Region

object OctaneGGRegionMapper {
    /**
     * Converts a region string from the Octane.gg api to a [Region] enum entry.
     */
    fun fromString(
        region: String,
    ): Region {
        return when (region) {
            "NA" -> Region.NA
            "EU" -> Region.EU
            "OCE" -> Region.OCE
            "SAM" -> Region.SAM
            "ASIA" -> Region.APAC
            "ME" -> Region.MENA
            "INT" -> Region.INT
            "AF" -> Region.SSA
            else -> Region.Unknown
        }
    }
}
