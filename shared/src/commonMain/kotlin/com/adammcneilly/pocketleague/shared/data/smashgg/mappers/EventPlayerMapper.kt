package com.adammcneilly.pocketleague.shared.data.smashgg.mappers

import com.adammcneilly.pocketleague.core.models.Player
import com.adammcneilly.pocketleague.shared.graphql.fragment.EventPlayerFragment

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
