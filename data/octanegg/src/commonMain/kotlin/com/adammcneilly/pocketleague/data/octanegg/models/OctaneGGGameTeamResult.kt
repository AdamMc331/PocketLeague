package com.adammcneilly.pocketleague.data.octanegg.models

import com.adammcneilly.pocketleague.core.models.GameTeamResult
import com.adammcneilly.pocketleague.core.models.Stats
import com.adammcneilly.pocketleague.core.models.Team
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The result of a [team] inside an [OctaneGGGame].
 */
@Serializable
data class OctaneGGGameTeamResult(
    @SerialName("winner")
    val gameWinner: Boolean? = null,
    @SerialName("matchWinner")
    val matchWinner: Boolean? = null,
    @SerialName("players")
    val players: List<OctaneGGPlayerStats>? = null,
    @SerialName("team")
    val team: OctaneGGTeamStats? = null,
)

/**
 * Convert an [OctaneGGGameTeamResult] to a [GameTeamResult] in our domain.
 */
fun OctaneGGGameTeamResult.toGameTeamResult(): GameTeamResult {
    return GameTeamResult(
        goals = this.team?.stats?.core?.goals ?: 0,
        winner = this.gameWinner == true,
        team = this.team?.team?.toTeam() ?: Team(),
        matchWinner = this.matchWinner == true,
        teamStats = this.team?.stats?.toStats() ?: Stats(),
        players = this.players?.map(OctaneGGPlayerStats::toGamePlayerResult).orEmpty(),
    )
}
