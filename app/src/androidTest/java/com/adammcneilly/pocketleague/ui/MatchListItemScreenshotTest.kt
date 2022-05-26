package com.adammcneilly.pocketleague.ui

import androidx.compose.ui.test.junit4.createComposeRule
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.MatchTeamResult
import com.adammcneilly.pocketleague.core.models.Team
import com.karumi.shot.ScreenshotTest
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

@Ignore("Currently unable to record updated snapshots, so let's ignore these for now.")
class MatchListItemScreenshotTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderPlaceholderMatchListItem() {
        val displayModel = Match()

        composeTestRule.setContent {
            MatchListItem(match = displayModel)
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun renderBlueTeamWinner() {
        val match = Match(
            id = "",
            event = Event(
                name = "Test Event",
            ),
            date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            blueTeam = MatchTeamResult(
                score = 5,
                winner = true,
                team = Team(
                    name = "Pittsburgh Knights",
                ),
            ),
            orangeTeam = MatchTeamResult(
                score = 0,
                winner = false,
                team = Team(
                    name = "G2 Esports",
                ),
            ),
        )

        composeTestRule.setContent {
            MatchListItem(match = match)
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun renderOrangeTeamWinner() {
        val match = Match(
            id = "",
            event = Event(
                name = "Test Event",
            ),
            date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            blueTeam = MatchTeamResult(
                score = 0,
                winner = false,
                team = Team(
                    name = "Pittsburgh Knights",
                ),
            ),
            orangeTeam = MatchTeamResult(
                score = 5,
                winner = true,
                team = Team(
                    name = "G2 Esports",
                ),
            ),
        )

        composeTestRule.setContent {
            MatchListItem(match = match)
        }

        compareScreenshot(composeTestRule)
    }
}
