package com.adammcneilly.pocketleague.data.local.mappers

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.sqldelight.LocalMatch

fun LocalMatch.toMatch(): Match {
    TODO("Not yet implemented")
}

fun Match.toLocalMatch(): LocalMatch {
    return LocalMatch(
        id = this.id,
        dateUTC = this.dateUTC,
        eventId = this.event.id,
        blueTeamId = this.blueTeam.team.id,
        orangeTeamId = this.orangeTeam.team.id,
        blueTeamGameWins = this.blueTeam.score.toLong(),
        orangeTeamGameWins = this.orangeTeam.score.toLong(),
        formatType = this.format.type,
        formatLength = this.format.length.toLong(),
    )
}
