package com.adammcneilly.pocketleague.data.local.sqldelight.mappers

import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.sqldelight.LocalTeam

fun Team.toLocalTeam(): LocalTeam {
    return LocalTeam(
        id = this.id,
        name = this.name,
        lightImageURL = this.lightThemeImageURL,
        darkImageURL = this.darkThemeImageURL,
        isFavorite = this.isFavorite,
        isActive = this.isActive,
    )
}

fun LocalTeam.toTeam(): Team {
    return Team(
        id = this.id,
        name = this.name,
        lightThemeImageURL = this.lightImageURL,
        darkThemeImageURL = this.darkImageURL,
        isFavorite = this.isFavorite,
        isActive = this.isActive ?: false,
    )
}
