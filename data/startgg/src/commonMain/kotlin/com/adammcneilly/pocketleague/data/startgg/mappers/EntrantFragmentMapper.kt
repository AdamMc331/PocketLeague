package com.adammcneilly.pocketleague.data.startgg.mappers

import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.data.startgg.fragment.TeamFragment

/**
 * Converts a [TeamFragment] GQL model to a [Team] within the pocket league domain.
 */
fun TeamFragment.toTeam(): Team {
    return Team(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        lightThemeImageURL = this.images?.firstOrNull()?.url,
    )
}
