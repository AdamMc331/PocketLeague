package com.adammcneilly.pocketleague.data.local.mappers

import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.sqldelight.LocalTeam

/**
 * Convert a [Team] from our domain to a SqlDelight [LocalTeam].
 */
fun Team.toLocalTeam(): LocalTeam {
    return LocalTeam(
        id = this.id,
        name = this.name,
        imageURL = this.imageUrl,
        isFavorite = this.isFavorite,
    )
}

/**
 * Convert a SqlDelight [LocalTeam] to a [Team] from our domain.
 */
fun LocalTeam.toTeam(): Team {
    return Team(
        id = this.id,
        name = this.name,
        imageUrl = this.imageURL,
        isFavorite = this.isFavorite,
    )
}
