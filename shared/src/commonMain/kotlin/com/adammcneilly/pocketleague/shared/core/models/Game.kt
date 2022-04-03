package com.adammcneilly.pocketleague.shared.core.models

/**
 * Defines a 5 minute game between two RLCS teams.
 *
 * @property[id] The unique identifier of this game.
 * @property[winnerId] The ID of the [Team] that won this game.
 */
data class Game(
    val id: String,
    val winnerId: String,
)
