package com.adammcneilly.pocketleague.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.adammcneilly.pocketleague.feature.feed.FeedViewState
import com.adammcneilly.pocketleague.setPocketLeagueContent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeedContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderEmptyRecentMatches() {
        val viewState = com.adammcneilly.pocketleague.feature.feed.FeedViewState(
            recentMatches = emptyList()
        )

        composeTestRule.setPocketLeagueContent {
            FeedContent(viewState = viewState)
        }

        composeTestRule
            .onNodeWithText("There are no recent match results.")
            .assertIsDisplayed()
    }

    @Test
    fun renderWithRecentMatches() {
        val viewState = com.adammcneilly.pocketleague.feature.feed.FeedViewState(
            recentMatches = listOf(TestDisplayModel.matchDetailBlueWinner)
        )

        composeTestRule.setPocketLeagueContent {
            FeedContent(viewState = viewState)
        }

        composeTestRule
            .onNodeWithText("Knights [winner]")
            .assertIsDisplayed()
    }
}
