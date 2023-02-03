package com.adammcneilly.pocketleague.core.models

/**
 * Defines information about the reuslt of a [team] within a game.
 *
 * @property[goals] The number of goals scored by this team during the game.
 * @property[winner] True if this team won the specific game.
 * @property[matchWinner] True if this team won the match between both teams.
 * @property[team] Detailed information about the [Team] playing in the match.
 * @property[teamStats] The detailed [Stats] for this [team] in the game.
 * @property[players] The players for this [team] and how they performed in this game.
 */
data class GameTeamResult(
    val goals: Int = -1,
    val winner: Boolean = false,
    val matchWinner: Boolean = false,
    val team: Team = Team(),
    val teamStats: Stats = Stats(),
    val players: List<GamePlayerResult> = emptyList(),
)
