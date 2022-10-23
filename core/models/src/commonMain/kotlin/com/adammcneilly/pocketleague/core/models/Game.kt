package com.adammcneilly.pocketleague.core.models

/**
 * Information about a single [Game] inside a [Match] between two teams.
 */
data class Game(
    val id: String = "",
    val blue: GameTeamResult = GameTeamResult(),
    val orange: GameTeamResult = GameTeamResult(),
    val map: String = "",
    val number: Int = 0,
    val duration: Int = GAME_DEFAULT_DURATION_SECONDS,
) {

    companion object {
        /**
         * A standard game of Rocket League is 5 minutes, or 300 seconds.
         *
         * The duration property wll be longer in an OverTime game.
         */
        const val GAME_DEFAULT_DURATION_SECONDS = 300
    }
}
