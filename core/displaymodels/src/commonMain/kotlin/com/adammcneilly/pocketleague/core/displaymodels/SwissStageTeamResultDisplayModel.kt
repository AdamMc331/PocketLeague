package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.SwissStageTeamResult

/**
 * User friendly representation of how a team preformed
 * during the swiss stage of an event.
 *
 * @property[standing] The placement of the team [aka X/num teams]
 * @property[team] The [TeamOverviewDisplayModel] of this participant.
 * @property[matchRecord] The win/loss record for the matches in the swiss round (ex: 3-0)
 * @property[gameRecord] The win/loss record for games in this swiss stage (ex: 9-1)
 * @property[gameDifferential] The number of games won compared to games lost, simplified version of [gameRecord]. (Ex: +8)
 */
data class SwissStageTeamResultDisplayModel(
    val standing: Int,
    val team: TeamOverviewDisplayModel,
    val matchRecord: String,
    val gameRecord: String,
    val gameDifferential: String,
    val overlineText: String,
) {
    companion object
}

fun SwissStageTeamResult.toDisplayModel(): SwissStageTeamResultDisplayModel {
    val gameDifferentialVal = this.gameWins - this.gameLosses
    // If positive, need to include plus symbol
    // see if there's a helper for this already somewhere?
    val gameDifferentialString = if (gameDifferentialVal > 0) {
        "+$gameDifferentialVal"
    } else {
        gameDifferentialVal.toString()
    }

    val overlineText = if (this.qualified) {
        "Qualified"
    } else {
        "Eliminated"
    }

    return SwissStageTeamResultDisplayModel(
        standing = 0,
        team = this.team.toOverviewDisplayModel(),
        matchRecord = "${this.matchWins}-${this.matchLosses}",
        gameRecord = "${this.gameWins}-${this.gameLosses}",
        gameDifferential = gameDifferentialString,
        overlineText = overlineText,
    )
}
