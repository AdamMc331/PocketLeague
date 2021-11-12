package com.adammcneilly.pocketleague.teamoverview.ui

import com.adammcneilly.pocketleague.core.domain.models.Team
import com.adammcneilly.pocketleague.core.ui.UIImage

data class TeamOverviewDisplayModel(
    val name: String,
    val logoImage: UIImage,
)

fun Team.toOverviewDisplayModel(): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        name = this.name,
        logoImage = UIImage.Remote(this.logoImageUrl)
    )
}
