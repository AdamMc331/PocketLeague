package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL

val TeamOverviewDisplayModel.Companion.test: TeamOverviewDisplayModel
    get() = TeamOverviewDisplayModel(
        teamId = "6020bd98f1e4807cc700dc08",
        name = "Knights",
        imageUrl = ThemedImageURL(
            lightThemeImageUrl = "https://griffon.octane.gg/teams/pittsburgh-knights.png",
        ),
    )
