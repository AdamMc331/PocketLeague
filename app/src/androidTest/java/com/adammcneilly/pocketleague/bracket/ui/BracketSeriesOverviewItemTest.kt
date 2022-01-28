package com.adammcneilly.pocketleague.bracket.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.adammcneilly.pocketleague.fakes.fakeSeriesOverviewDisplayModel
import org.junit.Rule
import org.junit.Test

class BracketSeriesOverviewItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderWithTeamOneWinner() {
        val displayModel = fakeSeriesOverviewDisplayModel.copy(
            teamOneWins = 1,
            teamTwoWins = 0,
        )

        composeTestRule.setContent {
            BracketSeriesOverviewItem(
                seriesOverview = displayModel,
            )
        }

        composeTestRule.onNodeWithText(displayModel.teamOne.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(displayModel.teamOneWins.toString()).assertIsDisplayed()

        composeTestRule.onNodeWithText(displayModel.teamTwo.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(displayModel.teamTwoWins.toString()).assertIsDisplayed()
    }

    @Test
    fun renderWithTeamTwoWinner() {
        val displayModel = fakeSeriesOverviewDisplayModel.copy(
            teamOneWins = 0,
            teamTwoWins = 1,
        )

        composeTestRule.setContent {
            BracketSeriesOverviewItem(
                seriesOverview = displayModel,
            )
        }

        composeTestRule.onNodeWithText(displayModel.teamOne.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(displayModel.teamOneWins.toString()).assertIsDisplayed()

        composeTestRule.onNodeWithText(displayModel.teamTwo.name).assertIsDisplayed()
        composeTestRule.onNodeWithText(displayModel.teamTwoWins.toString()).assertIsDisplayed()
    }
}
