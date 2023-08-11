package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.Player

/**
 * User friendly representation of a player.
 */
data class PlayerDisplayModel(
    val id: String,
    val tag: String,
    val role: String?,
)

/**
 * Converts a [Player] to its corresponding [PlayerDisplayModel]
 */
fun Player.toDisplayModel(): PlayerDisplayModel {
    // Maybe roles can be an enum instead of strings?
    val role = when {
        this.isCoach -> "Coach"
        this.isSubstitute -> "Substitute"
        else -> null
    }

    return PlayerDisplayModel(
        id = this.id,
        tag = this.tag,
        role = role,
    )
}
