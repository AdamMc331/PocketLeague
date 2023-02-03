package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.Player

/**
 * User friendly representation of a player.
 */
data class PlayerDisplayModel(
    val id: String,
    val tag: String
)

/**
 * Converts a [Player] to its corresponding [PlayerDisplayModel]
 */
fun Player.toDisplayModel(): PlayerDisplayModel {
    return PlayerDisplayModel(
        id = this.id,
        tag = this.tag
    )
}
