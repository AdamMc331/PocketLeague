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
) {

    /**
     * This logic only works if we've completed the swiss stage, we'll need to consider ongoing events
     * in the future.
     */
    val qualified: Boolean
        get() = matchWins > matchLosses
}
