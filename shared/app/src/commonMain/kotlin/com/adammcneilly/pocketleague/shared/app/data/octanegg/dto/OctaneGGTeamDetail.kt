package com.adammcneilly.pocketleague.shared.app.data.octanegg.dto

import com.adammcneilly.pocketleague.shared.app.core.models.Team
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
) {
    /**
     * Converts an [OctaneGGTeamDetail] entity to a [Team] in our domain.
     */
    fun toTeam(): Team {
        requireNotNull(team) {
            "Cannot parse OctaneGGTeamDetail without team entity."
        }

        return this.team.toTeam()
    }
}
