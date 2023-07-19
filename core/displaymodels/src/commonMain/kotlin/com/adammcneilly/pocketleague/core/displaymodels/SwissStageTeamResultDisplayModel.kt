package com.adammcneilly.pocketleague.core.displaymodels

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
) {
    companion object
}
