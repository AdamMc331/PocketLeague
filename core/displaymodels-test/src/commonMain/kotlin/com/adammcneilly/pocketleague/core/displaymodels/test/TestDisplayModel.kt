package com.adammcneilly.pocketleague.core.displaymodels.test

import com.adammcneilly.pocketleague.core.displaymodels.CoreStatsDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.EventStageSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.GameTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL
import com.adammcneilly.pocketleague.core.models.StageRound

object TestDisplayModel {
    val coreStats = CoreStatsDisplayModel(
        score = 1000,
        goals = 7,
        assists = 5,
        saves = 3,
        shots = 1,
    )

    val cheese = PlayerDisplayModel(
        id = "cheeseId",
        tag = "CHEESE",
    )

    val sosa = PlayerDisplayModel(
        id = "sosaId",
        tag = "sosa",
    )

    val zps = PlayerDisplayModel(
        id = "zpsId",
        tag = "ZPS",
    )

    val knights = TeamOverviewDisplayModel(
        teamId = "6020bd98f1e4807cc700dc08",
        name = "Knights",
        imageUrl = ThemedImageURL(
            lightThemeImageURL = "https://griffon.octane.gg/teams/pittsburgh-knights.png",
        ),
        regionName = "North America",
    )

    val g2 = TeamOverviewDisplayModel(
        teamId = "6020bc70f1e4807cc70023a5",
        name = "G2 Esports",
        imageUrl = ThemedImageURL(
            lightThemeImageURL = "https://griffon.octane.gg/teams/g2-esports.png",
        ),
        regionName = "North America",
    )

    val matchTeamResultWinner = MatchTeamResultDisplayModel(
        team = knights,
        score = 7,
        winner = true,
        coreStats = coreStats,
        players = emptyList(),
    )

    val matchTeamResultLoser = MatchTeamResultDisplayModel(
        team = g2,
        score = 1,
        winner = false,
        coreStats = coreStats,
        players = emptyList(),
    )

    val stageRound = StageRound(
        number = 1,
        name = "First Round",
    )

    val matchDetailBlueWinner = MatchDetailDisplayModel(
        matchId = "matchId",
        localDate = "Jan 01, 2000",
        localTime = "12:00",
        eventName = "RLCS World Championship",
        stageName = "Playoffs",
        relativeDateTime = "1d ago",
        orangeTeamResult = matchTeamResultLoser,
        blueTeamResult = matchTeamResultWinner,
        round = stageRound,
    )

    val matchDetailOrangeWinner = matchDetailBlueWinner.copy(
        orangeTeamResult = matchTeamResultWinner,
        blueTeamResult = matchTeamResultLoser,
    )

    val gameTeamResultWinner = GameTeamResultDisplayModel(
        team = knights,
        goals = 7,
        winner = true,
        players = emptyList(),
    )

    val gameTeamResultLoser = GameTeamResultDisplayModel(
        team = g2,
        goals = 1,
        winner = false,
        players = emptyList(),
    )

    val gameDetailBlueWinner = GameDetailDisplayModel(
        orangeTeamResult = gameTeamResultLoser,
        blueTeamResult = gameTeamResultWinner,
        map = "Wasteland",
        gameNumber = "1",
        otLabel = null,
    )

    val gameDetailOrangeWinner = gameDetailBlueWinner.copy(
        orangeTeamResult = gameTeamResultWinner,
        blueTeamResult = gameTeamResultLoser,
    )

    val eventStageSummary = EventStageSummaryDisplayModel(
        stageId = "stageId",
        name = "Playoffs",
        startDate = "Jan 01, 2000",
        endDate = "Jan 02, 2000",
        lan = false,
        liquipedia = "liquipediaURL",
    )
}
