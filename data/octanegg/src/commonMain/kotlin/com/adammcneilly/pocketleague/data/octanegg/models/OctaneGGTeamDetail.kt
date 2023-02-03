package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.Team
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
    val team: OctaneGGTeamOverview? = null
)

/**
 * Converts an [OctaneGGTeamDetail] entity to a [Team] in our domain.
 */
fun OctaneGGTeamDetail.toTeam(): Team {
    return this.team?.toTeam() ?: Team()
}
