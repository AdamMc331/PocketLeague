package com.adammcneilly.pocketleague.android.designsystem.components

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL
import org.junit.Rule
import org.junit.Test

class MatchCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    private val winningTeam = MatchTeamResultDisplayModel(
        team = TeamOverviewDisplayModel(
            name = "Winner",
            teamId = "",
            imageUrl = ThemedImageURL(),
        ),
        score = 7,
        winner = true,
    )

    private val losingTeam = MatchTeamResultDisplayModel(
        team = TeamOverviewDisplayModel(
            name = "Loser",
            teamId = "",
            imageUrl = ThemedImageURL(),
        ),
        score = 1,
        winner = false,
    )

    private val blueWinnerMatch = MatchDetailDisplayModel(
        matchId = "",
        localDate = "Jan 01, 2000",
        localTime = "12:00",
        eventName = "RLCS World Championship",
        stageName = "Playoffs",
        relativeDateTime = "1d ago",
        orangeTeamResult = losingTeam,
        blueTeamResult = winningTeam,
    )

    private val orangeWinnerMatch = blueWinnerMatch.copy(
        orangeTeamResult = winningTeam,
        blueTeamResult = losingTeam,
    )

    @Test
    fun renderBlueTeamWinner() {
        paparazzi.snapshotScreen {
            MatchCard(match = blueWinnerMatch)
        }
    }

    @Test
    fun renderOrangeTeamWinner() {
        paparazzi.snapshotScreen {
            MatchCard(match = orangeWinnerMatch)
        }
    }

    @Test
    fun renderPlaceholder() {
        paparazzi.snapshotScreen {
            MatchCard(match = MatchDetailDisplayModel.placeholder)
        }
    }
}
