package com.adammcneilly.pocketleague.ui

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adammcneilly.pocketleague.android.designsystem.components.PlaceholderSemanticsKey
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.setPocketLeagueContent
import com.adammcneilly.pocketleague.shared.screens.feed.FeedViewState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeedContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderEmptyRecentMatches() {
        val viewState = FeedViewState(
            recentMatchesState = DataState.Success(emptyList()),
        )

        composeTestRule.setPocketLeagueContent {
            FeedContent(viewState = viewState)
        }

        composeTestRule
            .onNodeWithText("There are no recent match results.")
            .assertIsDisplayed()
    }

    @Test
    fun renderLoadingRecentMatches() {
        val viewState = FeedViewState(
            recentMatchesState = DataState.Loading
        )

        composeTestRule.setPocketLeagueContent {
            FeedContent(viewState = viewState)
        }

        composeTestRule
            .onAllNodes(SemanticsMatcher.expectValue(PlaceholderSemanticsKey, true))
            .assertCountEquals(2)
    }

    @Test
    fun renderWithRecentMatches() {
        val viewState = FeedViewState(
            recentMatchesState = DataState.Success(listOf(TestDisplayModel.matchDetailBlueWinner)),
        )

        composeTestRule.setPocketLeagueContent {
            FeedContent(viewState = viewState)
        }

        composeTestRule
            .onNodeWithText("Knights [winner]")
            .assertIsDisplayed()
    }
}
