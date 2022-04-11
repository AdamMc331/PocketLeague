package com.adammcneilly.pocketleague.team.implementation.octanegg.mappers

import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.team.implementation.octanegg.dtos.PlayerDTO
import com.adammcneilly.pocketleague.team.implementation.octanegg.dtos.TeamDTO

/**
 * Converts a [TeamDTO] from the octane.gg API to a [Team] entity.
 */
internal fun TeamDTO.toTeam(): Team {
    return Team(
        name = this.team?.name ?: "N/A",
        lightThemeLogoImageUrl = this.team?.image,
        darkThemeLogoImageUrl = this.team?.image,
        roster = this.players?.map(PlayerDTO::toPlayer).orEmpty(),
    )
}
