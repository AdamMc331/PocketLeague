package com.adammcneilly.pocketleague.teamoverview.ui

import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.core.ui.FlagResProvider
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.player.ui.PlayerDisplayModel
import com.adammcneilly.pocketleague.player.ui.toDisplayModel

/**
 * Defines UI information for a team item to be shown at a high level.
 */
data class TeamOverviewDisplayModel(
    val name: String,
    val lightLogoImage: UIImage,
    val darkLogoImage: UIImage = lightLogoImage,
    val roster: List<PlayerDisplayModel>,
)

/**
 * Converts a [Team] entity to a [TeamOverviewDisplayModel] without roster information.
 */
fun Team.toOverviewDisplayModelWithoutRoster(): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        name = this.name,
        lightLogoImage = UIImage.Remote(this.lightThemeLogoImageUrl.orEmpty()),
        darkLogoImage = UIImage.Remote(this.darkThemeLogoImageUrl.orEmpty()),
        roster = emptyList(),
    )
}

/**
 * Converts a [Team] entity to a [TeamOverviewDisplayModel] including roster information.
 */
fun Team.toOverviewDisplayModel(
    flagResProvider: FlagResProvider,
): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        name = this.name,
        lightLogoImage = UIImage.Remote(this.lightThemeLogoImageUrl.orEmpty()),
        darkLogoImage = UIImage.Remote(this.darkThemeLogoImageUrl.orEmpty()),
        roster = this.roster.map { player ->
            player.toDisplayModel(flagResProvider)
        },
    )
}
