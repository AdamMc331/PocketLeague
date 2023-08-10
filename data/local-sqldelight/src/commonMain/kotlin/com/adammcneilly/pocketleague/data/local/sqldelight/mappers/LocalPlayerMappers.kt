package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.sqldelight.LocalPlayer

/**
 * Converts a [LocalPlayer] SQLDelight entity to a [Player] from our domain.
 */
fun LocalPlayer.toPlayer(): Player {
    return Player(
        id = this.id,
        slug = this.slug,
        tag = this.tag,
        countryCode = this.countryCode.orEmpty(),
        isCoach = this.isCoach,
        name = this.name.orEmpty(),
        currentTeamId = this.currentTeamId,
    )
}

/**
 * Converts a [Player] into a SQLDelight [LocalPlayer].
 */
fun Player.toLocalPlayer(): LocalPlayer {
    return LocalPlayer(
        id = this.id,
        tag = this.tag,
        name = this.name,
        slug = this.slug,
        isCoach = this.isCoach,
        countryCode = this.countryCode,
        currentTeamId = this.currentTeamId,
    )
}
