package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.SwissStageTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel

fun SwissStageTeamResultDisplayModel.Companion.firstPlace(): SwissStageTeamResultDisplayModel {
    return SwissStageTeamResultDisplayModel(
        standing = 1,
        team = TeamOverviewDisplayModel.teamVitality(),
        matchRecord = "3-0",
        gameRecord = "9-1",
        gameDifferential = "+8",
    )
}

fun SwissStageTeamResultDisplayModel.Companion.fallMajorList(): List<SwissStageTeamResultDisplayModel> {
    return listOf(
        SwissStageTeamResultDisplayModel(
            standing = 1,
            team = TeamOverviewDisplayModel.testTeam("Karmine Corp"),
            matchRecord = "3-0",
            gameRecord = "9-1",
            gameDifferential = "+8",
        ),
        SwissStageTeamResultDisplayModel(
            standing = 2,
            team = TeamOverviewDisplayModel.testTeam("Oxygen Esports"),
            matchRecord = "3-0",
            gameRecord = "9-2",
            gameDifferential = "+7",
        ),
        SwissStageTeamResultDisplayModel(
            standing = 3,
            team = TeamOverviewDisplayModel.testTeam("Gen.G Mobil1 Racing"),
            matchRecord = "3-1",
            gameRecord = "9-4",
            gameDifferential = "+5",
        ),
        SwissStageTeamResultDisplayModel(
            standing = 4,
            team = TeamOverviewDisplayModel.testTeam("Team Secret"),
            matchRecord = "3-1",
            gameRecord = "9-5",
            gameDifferential = "+4",
        ),
        SwissStageTeamResultDisplayModel(
            standing = 5,
            team = TeamOverviewDisplayModel.testTeam("Version1"),
            matchRecord = "3-1",
            gameRecord = "9-6",
            gameDifferential = "+3",
        ),
        SwissStageTeamResultDisplayModel(
            standing = 6,
            team = TeamOverviewDisplayModel.testTeam("Team Liquid"),
            matchRecord = "3-2",
            gameRecord = "13-9",
            gameDifferential = "+4",
        ),
        SwissStageTeamResultDisplayModel(
            standing = 7,
            team = TeamOverviewDisplayModel.testTeam("FaZe Clan"),
            matchRecord = "3-2",
            gameRecord = "10-9",
            gameDifferential = "+1",
        ),
        SwissStageTeamResultDisplayModel(
            standing = 8,
            team = TeamOverviewDisplayModel.testTeam("Moist Esports"),
            matchRecord = "3-2",
            gameRecord = "10-11",
            gameDifferential = "-1",
        ),
    )
}
