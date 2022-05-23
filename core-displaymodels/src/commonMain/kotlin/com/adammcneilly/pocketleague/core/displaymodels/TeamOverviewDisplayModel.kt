package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.Team

/**
 * Displays overview information about a team in a user friendly fashion.
 */
data class TeamOverviewDisplayModel(
    val teamId: String = "",
    val name: String = "",
    val lightThemeImageUrl: String? = null,
    val darkThemeImageUrl: String? = lightThemeImageUrl,
)

/**
 * Converts a [Team] to a [TeamOverviewDisplayModel].
 */
fun Team.toOverviewDisplayModel(): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        teamId = this.id,
        name = this.name,
        lightThemeImageUrl = this.imageUrl,
    )
}
