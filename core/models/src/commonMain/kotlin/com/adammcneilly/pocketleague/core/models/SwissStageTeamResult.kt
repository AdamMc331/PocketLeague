package com.adammcneilly.pocketleague.core.models

/**
 * Represents how a given [team] preformed in the swiss stage of an event.
 */
data class SwissStageTeamResult(
    val team: Team,
    val matchWins: Int,
    val matchLosses: Int,
    val gameWins: Int,
    val gameLosses: Int,
    val eventId: String,
    val stageId: String,
)
