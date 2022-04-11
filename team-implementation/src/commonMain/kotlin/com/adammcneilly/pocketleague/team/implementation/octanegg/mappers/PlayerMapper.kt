package com.adammcneilly.pocketleague.team.implementation.octanegg.mappers

import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.team.implementation.octanegg.dtos.PlayerDTO

/**
 * Converts a [PlayerDTO] from octane.gg API into a [Player] entity.
 */
internal fun PlayerDTO.toPlayer(): Player {
    return Player(
        countryCode = this.country ?: "N/A",
        gamerTag = this.tag ?: "N/A",
        realName = this.name ?: "N/A",
    )
}
