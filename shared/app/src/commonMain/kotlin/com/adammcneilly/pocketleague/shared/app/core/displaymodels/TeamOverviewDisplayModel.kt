package com.adammcneilly.pocketleague.shared.app.core.displaymodels

import com.adammcneilly.pocketleague.shared.app.core.models.Region
import com.adammcneilly.pocketleague.shared.app.core.models.Team

/**
 * Displays overview information about a team in a user friendly fashion.
 */
data class TeamOverviewDisplayModel(
    val teamId: String,
    val name: String,
    val imageUrl: ThemedImageURL,
    val region: RegionDisplayModel,
    val isPlaceholder: Boolean = false,
    val isFavorite: Boolean = false,
) {
    constructor(team: Team) : this(
        teamId = team.id,
        name = team.name,
        imageUrl = team.themedImageUrl(),
        isFavorite = team.isFavorite,
        region = RegionDisplayModel(team.region),
    )

    companion object {
        val placeholder = TeamOverviewDisplayModel(
            teamId = "",
            name = "",
            imageUrl = ThemedImageURL(),
            isPlaceholder = true,
            region = RegionDisplayModel(Region.Unknown),
        )
    }
}

private fun Team.themedImageUrl(): ThemedImageURL {
    return ThemedImageURL(
        lightThemeImageURL = this.lightThemeImageURL,
        darkThemeImageURL = this.darkThemeImageURL,
    )
}
