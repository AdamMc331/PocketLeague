package com.adammcneilly.pocketleague.android.designsystem.components

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.ThemedImageURL
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class MatchCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

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
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
        ) {
            MatchCard(match = blueWinnerMatch)
        }
    }

    @Test
    fun renderOrangeTeamWinner() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
        ) {
            MatchCard(match = orangeWinnerMatch)
        }
    }

    @Test
    fun renderPlaceholder() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
        ) {
            MatchCard(match = MatchDetailDisplayModel.placeholder)
        }
    }
}
