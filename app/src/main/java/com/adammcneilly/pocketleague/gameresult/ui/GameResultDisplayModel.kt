package com.adammcneilly.pocketleague.gameresult.ui

/**
 * Defines the UI configuration for the result of an individual game.
 *
 * NOTE: Should we define orange/blue team? Can we? Should winner be an enum?
 *
 * @property[stadium] The RL map that the game took place on.
 * @property[teamOneScore] The score of the first team.
 * @property[teamTwoScore] The score of the second team.
 */
data class GameResultDisplayModel(
    val stadium: String,
    val teamOneScore: Int,
    val teamTwoScore: Int,
) {

    val teamOneWinner: Boolean
        get() = teamOneScore > teamTwoScore

    val teamTwoWinner: Boolean
        get() = teamTwoScore > teamOneScore
}
