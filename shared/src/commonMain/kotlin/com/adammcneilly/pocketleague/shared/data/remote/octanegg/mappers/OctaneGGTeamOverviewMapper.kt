package com.adammcneilly.pocketleague.shared.data.remote.octanegg.mappers

import com.adammcneilly.pocketleague.shared.data.remote.octanegg.models.OctaneGGTeamOverview
import com.adammcneilly.pocketleague.shared.models.Team

/**
 * Converts an [OctaneGGTeamOverview] to a [Team] entity.
 */
fun OctaneGGTeamOverview.toTeam(): Team {
    return Team(
        id = this.id!!,
        name = this.name!!,
        imageUrl = this.image,
    )
}
