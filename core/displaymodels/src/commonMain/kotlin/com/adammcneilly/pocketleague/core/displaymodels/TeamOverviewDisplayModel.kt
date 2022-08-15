package com.adammcneilly.pocketleague.core.displaymodels

import com.adammcneilly.pocketleague.core.models.Team

/**
 * Displays overview information about a team in a user friendly fashion.
 */
data class TeamOverviewDisplayModel(
    val teamId: String = "",
    val name: String = "",
    val imageUrl: ThemedImageURL = ThemedImageURL(),
)

/**
 * Converts a [Team] to a [TeamOverviewDisplayModel].
 */
fun Team.toOverviewDisplayModel(): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        teamId = this.id,
        name = this.name,
        imageUrl = ThemedImageURL(
            lightThemeImageUrl = this.imageUrl,
        ),
    )
}
