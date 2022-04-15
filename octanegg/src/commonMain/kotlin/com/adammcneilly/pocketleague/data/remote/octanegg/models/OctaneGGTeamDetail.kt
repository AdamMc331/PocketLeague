package com.adammcneilly.pocketleague.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a team within the octane.gg API domain.
 */
@Serializable
data class OctaneGGTeamDetail(
    @SerialName("players")
    val players: List<OctaneGGPlayer>? = null,
    @SerialName("team")
    val team: OctaneGGTeamOverview? = null,
)
