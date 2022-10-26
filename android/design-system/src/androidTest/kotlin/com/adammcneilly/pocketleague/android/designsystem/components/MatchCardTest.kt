package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.adammcneilly.pocketleague.android.designsystem.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.blueWinner
import org.junit.Rule
import org.junit.Test

class MatchCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderBlueWinner() {
        composeTestRule.setContent {
            PocketLeagueTheme {
                MatchCard(match = MatchDetailDisplayModel.blueWinner)
            }
        }

        composeTestRule
            .onNodeWithTag("blue_match_team_name")
            .assertTextEquals("Knights [winner]")

        composeTestRule
            .onNodeWithTag("orange_match_team_name")
            .assertTextEquals("G2 Esports")
    }
}