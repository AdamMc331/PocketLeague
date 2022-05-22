package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGGame
import com.adammcneilly.pocketleague.core.models.Game
import com.adammcneilly.pocketleague.core.models.GameTeamResult

/**
 * Converts an [OctaneGGGame] to a [Game] in our domain.
 */
fun OctaneGGGame.toGame(): Game {
    return Game(
        id = this.id.orEmpty(),
        blue = this.blue?.toGameTeamResult() ?: GameTeamResult(),
        orange = this.orange?.toGameTeamResult() ?: GameTeamResult(),
        map = this.map?.name.orEmpty(),
        number = this.number ?: 0,
    )
}
