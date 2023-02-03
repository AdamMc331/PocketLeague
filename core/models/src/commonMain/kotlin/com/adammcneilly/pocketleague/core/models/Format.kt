package com.adammcneilly.pocketleague.core.models

/**
 * This represents the format of a matchup between two teams.
 * For a best of 7, we would have something like (type: best, length: 7)
 */
data class Format(
    val type: String = "",
    val length: Int = 0
) {

    /**
     * Depending on the [type] of format, return the number of games
     * required to win a matchup in this format.
     */
    fun numGamesRequiredToWin(): Int {
        return if (type == "best") {
            // In a best of X, the number of wins is half the games, plus one
            // since we can't end a series in a tie.
            // length will always be odd in this situation.
            (length / 2) + 1
        } else {
            // This could be buggy, because I don't know
            // all possible format types. In the future,
            // we should enumerate all of them and have consistent
            // logic here.
            length
        }
    }
}
