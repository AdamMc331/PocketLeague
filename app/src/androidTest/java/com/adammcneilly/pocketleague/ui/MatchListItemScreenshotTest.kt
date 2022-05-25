package com.adammcneilly.pocketleague.ui

import androidx.compose.ui.test.junit4.createComposeRule
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

class MatchListItemScreenshotTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderPlaceholderMatchListItem() {
        val displayModel = MatchDetailDisplayModel()

        composeTestRule.setContent {
            MatchListItem(displayModel = displayModel)
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun renderBlueTeamWinner() {
        val match = MatchDetailDisplayModel(
            matchId = "",
            eventName = "Test Event",
            relativeDate = "0d ago",
            blueTeamResult = MatchTeamResultDisplayModel(
                score = "5",
                winner = true,
                team = TeamOverviewDisplayModel(
                    name = "Pittsburgh Knights",
                ),
            ),
            orangeTeamResult = MatchTeamResultDisplayModel(
                score = "0",
                winner = false,
                team = TeamOverviewDisplayModel(
                    name = "G2 Esports",
                ),
            ),
        )

        composeTestRule.setContent {
            MatchListItem(displayModel = match)
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun renderOrangeTeamWinner() {
        val match = MatchDetailDisplayModel(
            matchId = "",
            eventName = "Test Event",
            relativeDate = "0d ago",
            blueTeamResult = MatchTeamResultDisplayModel(
                score = "0",
                winner = false,
                team = TeamOverviewDisplayModel(
                    name = "Pittsburgh Knights",
                ),
            ),
            orangeTeamResult = MatchTeamResultDisplayModel(
                score = "5",
                winner = true,
                team = TeamOverviewDisplayModel(
                    name = "G2 Esports",
                ),
            ),
        )

        composeTestRule.setContent {
            MatchListItem(displayModel = match)
        }

        compareScreenshot(composeTestRule)
    }
}
