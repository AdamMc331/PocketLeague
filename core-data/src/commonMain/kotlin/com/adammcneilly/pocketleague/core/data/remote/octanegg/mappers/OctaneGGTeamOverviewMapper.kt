package com.adammcneilly.pocketleague.core.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.data.remote.octanegg.models.OctaneGGTeamOverview
import com.adammcneilly.pocketleague.core.models.Team

/**
 * Converts an [OctaneGGTeamOverview] to a [Team] entity.
 */
fun OctaneGGTeamOverview.toTeam(): Team {
    return Team(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        imageUrl = this.image,
    )
}
