package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL

val TeamOverviewDisplayModel.Companion.knights: TeamOverviewDisplayModel
    get() = TeamOverviewDisplayModel(
        teamId = "6020bd98f1e4807cc700dc08",
        name = "Knights",
        imageUrl = ThemedImageURL(
            lightThemeImageUrl = "https://griffon.octane.gg/teams/pittsburgh-knights.png",
        ),
    )

val TeamOverviewDisplayModel.Companion.g2: TeamOverviewDisplayModel
    get() = TeamOverviewDisplayModel(
        teamId = "6020bc70f1e4807cc70023a5",
        name = "G2 Esports",
        imageUrl = ThemedImageURL(
            lightThemeImageUrl = "https://griffon.octane.gg/teams/g2-esports.png",
        ),
    )
