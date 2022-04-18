package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName

/**
 * Tracks any number of statistics relevant to a player or team.
 */
data class OctaneGGStats(
    @SerialName("core")
    val core: OctaneGGCoreStats? = null,
)
