package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.match.MatchCard
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import kotlin.test.Test

class MatchCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderBlueWinner() {
        var clickedId: Match.Id? = null

        composeTestRule.setContent {
            PocketLeagueTheme {
                MatchCard(
                    match = TestDisplayModel.matchDetailBlueWinner,
                    onClick = {
                        clickedId = it
                    },
                )
            }
        }

        composeTestRule
            .onNodeWithText("Knights [icon]")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("G2 Esports")
            .assertIsDisplayed()

        composeTestRule
            .onNode(hasClickAction())
            .performClick()

        assertEquals(
            TestDisplayModel.matchDetailBlueWinner.matchId,
            clickedId,
        )
    }

    @Test
    fun handleClickForPlaceholder() {
        var clickedId: Match.Id? = null

        composeTestRule.setContent {
            PocketLeagueTheme {
                MatchCard(
                    match = MatchDetailDisplayModel.placeholder,
                    onClick = {
                        clickedId = it
                    },
                )
            }
        }

        composeTestRule
            .onNode(hasClickAction())
            .performClick()

        assertNull(clickedId)
    }
}
