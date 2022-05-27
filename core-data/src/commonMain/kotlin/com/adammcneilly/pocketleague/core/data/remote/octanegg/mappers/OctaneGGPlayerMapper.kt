package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGPlayer
import com.adammcneilly.pocketleague.core.models.Player

/**
 * Converts an [OctaneGGPlayer] to a [Player].
 */
fun OctaneGGPlayer.toPlayer(): Player {
    return Player(
        id = this.id.orEmpty(),
        slug = this.slug.orEmpty(),
        tag = this.tag.orEmpty(),
        country = this.country.orEmpty(),
    )
}
