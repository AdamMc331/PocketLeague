package com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGMatchTeamResult
import com.adammcneilly.pocketleague.shared.models.MatchTeamResult

/**
 * Converts an [OctaneGGMatchTeamResult] to a [MatchTeamResult] entity.
 */
fun OctaneGGMatchTeamResult.toMatchTeamResult(): MatchTeamResult {
    return MatchTeamResult(
        score = this.score ?: 0,
        winner = this.winner == true,
        team = this.team?.team?.toTeam()!!,
    )
}