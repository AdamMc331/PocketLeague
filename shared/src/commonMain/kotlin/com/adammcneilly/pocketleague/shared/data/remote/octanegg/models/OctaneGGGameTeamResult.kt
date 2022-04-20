package com.adammcneilly.pocketleague.shared.data.remote.octanegg.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The result of a [team] inside an [OctaneGGGame].
 */
@Serializable
data class OctaneGGGameTeamResult(
    @SerialName("matchWinner")
    val matchWinner: Boolean? = null,
    @SerialName("players")
    val players: List<OctaneGGPlayerStats>? = null,
    @SerialName("team")
    val team: OctaneGGTeamStats? = null
)
