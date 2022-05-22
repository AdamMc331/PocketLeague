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
)
