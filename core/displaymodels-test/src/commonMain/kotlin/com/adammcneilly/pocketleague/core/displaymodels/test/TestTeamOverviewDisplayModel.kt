package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL

/**
 * Creates a test implementation of [TeamOverviewDisplayModel] for Team Vitality.
 */
fun TeamOverviewDisplayModel.Companion.teamVitality(): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        teamId = "vitalityId",
        name = "Team Vitality",
        imageUrl = ThemedImageURL(),
        regionName = "Europe",
    )
}

fun TeamOverviewDisplayModel.Companion.testTeam(
    name: String,
    regionName: String = "Europe",
): TeamOverviewDisplayModel {
    return TeamOverviewDisplayModel(
        teamId = name,
        name = name,
        imageUrl = ThemedImageURL(),
        regionName = regionName,
    )
}
