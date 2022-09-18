package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGGameTeamResult
import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGPlayerStats
import com.adammcneilly.pocketleague.core.models.GameTeamResult
import com.adammcneilly.pocketleague.core.models.Stats
import com.adammcneilly.pocketleague.core.models.Team

/**
 * Converts an [OctaneGGGameTeamResult] to a [GameTeamResult] in our domain.
 */
fun OctaneGGGameTeamResult.toGameTeamResult(): GameTeamResult {
    return GameTeamResult(
        goals = this.team?.stats?.core?.goals ?: 0,
        winner = this.gameWinner == true,
        team = this.team?.team?.toTeam() ?: Team(),
        matchWinner = this.matchWinner == true,
        teamStats = this.team?.stats?.toStats() ?: Stats(),
        players = this.players?.map(OctaneGGPlayerStats::toGamePlayerResult).orEmpty()
    )
}
