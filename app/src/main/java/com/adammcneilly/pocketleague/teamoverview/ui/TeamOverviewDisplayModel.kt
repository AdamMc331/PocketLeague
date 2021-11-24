package com.adammcneilly.pocketleague.teamoverview.ui

import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.core.ui.FlagResProvider
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.player.ui.PlayerDisplayModel
import com.adammcneilly.pocketleague.player.ui.toDisplayModel

data class TeamOverviewDisplayModel(
    val name: String,
    val lightLogoImage: UIImage,
    val darkLogoImage: UIImage = lightLogoImage,
    val roster: List<PlayerDisplayModel>,
)

fun Team.toOverviewDisplayModelWithoutRoster(): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        name = this.name,
        lightLogoImage = UIImage.Remote(this.lightThemeLogoImageUrl),
        darkLogoImage = UIImage.Remote(this.darkThemeLogoImageUrl),
        roster = emptyList(),
    )
}

fun Team.toOverviewDisplayModel(
    flagResProvider: FlagResProvider,
): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        name = this.name,
        lightLogoImage = UIImage.Remote(this.lightThemeLogoImageUrl),
        darkLogoImage = UIImage.Remote(this.darkThemeLogoImageUrl),
        roster = this.roster.map { player ->
            player.toDisplayModel(flagResProvider)
        },
    )
}
