package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.sqldelight.LocalMatch

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
        stageId = this.stage.id,
        blueTeamTotalScore = this.blueTeam.stats?.core?.score?.toLong() ?: 0,
        blueTeamTotalGoals = this.blueTeam.stats?.core?.goals?.toLong() ?: 0,
        blueTeamTotalShots = this.blueTeam.stats?.core?.shots?.toLong() ?: 0,
        blueTeamTotalSaves = this.blueTeam.stats?.core?.saves?.toLong() ?: 0,
        blueTeamTotalAssists = this.blueTeam.stats?.core?.assists?.toLong() ?: 0,
        blueTeamShootingPercentage = this.blueTeam.stats?.core?.shootingPercentage?.toDouble() ?: 0.0,
        orangeTeamTotalScore = this.orangeTeam.stats?.core?.score?.toLong() ?: 0,
        orangeTeamTotalGoals = this.orangeTeam.stats?.core?.goals?.toLong() ?: 0,
        orangeTeamTotalShots = this.orangeTeam.stats?.core?.shots?.toLong() ?: 0,
        orangeTeamTotalSaves = this.orangeTeam.stats?.core?.saves?.toLong() ?: 0,
        orangeTeamTotalAssists = this.orangeTeam.stats?.core?.assists?.toLong() ?: 0,
        orangeTeamShootingPercentage = this.orangeTeam.stats?.core?.shootingPercentage?.toDouble() ?: 0.0
    )
}
