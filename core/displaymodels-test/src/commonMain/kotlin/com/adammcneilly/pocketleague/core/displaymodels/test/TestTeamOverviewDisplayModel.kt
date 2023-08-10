package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL
import com.adammcneilly.pocketleague.core.displaymodels.toDisplayModel
import com.adammcneilly.pocketleague.core.models.EventRegion

/**
 * Creates a test implementation of [TeamOverviewDisplayModel] for Team Vitality.
 */
fun TeamOverviewDisplayModel.Companion.teamVitality(): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        teamId = "vitalityId",
        name = "Team Vitality",
        imageUrl = ThemedImageURL(),
        region = EventRegion.EU.toDisplayModel(),
    )
}
