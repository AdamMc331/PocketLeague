package com.adammcneilly.pocketleague.teamoverview.ui

import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.core.ui.FlagResProvider
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.player.ui.PlayerDisplayModel
import com.adammcneilly.pocketleague.player.ui.toDisplayModel

data class TeamOverviewDisplayModel(
    val name: String,
    val logoImage: UIImage,
    val roster: List<PlayerDisplayModel>,
)

fun Team.toOverviewDisplayModelWithoutRoster(): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        name = this.name,
        logoImage = UIImage.Remote(this.lightThemeLogoImageUrl),
        roster = emptyList(),
    )
}

fun Team.toOverviewDisplayModel(
    flagResProvider: FlagResProvider,
): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        name = this.name,
        logoImage = UIImage.Remote(this.lightThemeLogoImageUrl),
        roster = this.roster.map { player ->
            player.toDisplayModel(flagResProvider)
        },
    )
}
