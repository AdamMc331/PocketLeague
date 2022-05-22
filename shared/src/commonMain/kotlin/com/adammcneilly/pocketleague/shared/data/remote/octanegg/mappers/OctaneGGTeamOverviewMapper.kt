package com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGTeamOverview

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
