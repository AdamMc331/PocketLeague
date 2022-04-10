package com.adammcneilly.pocketleague.event.implementation.smashgg.mappers

import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.event.implementation.graphql.fragment.EventPlayerFragment

/**
 * Converting an [EventPlayerFragment] from the smash.gg API into a [Player] from our domain.
 */
fun EventPlayerFragment?.toPlayer(): Player {
    return Player(
        countryCode = "",
        gamerTag = this?.gamerTag.orEmpty(),
        realName = "",
    )
}
