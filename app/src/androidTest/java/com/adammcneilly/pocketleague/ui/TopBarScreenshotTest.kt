package com.adammcneilly.pocketleague.ui

import androidx.compose.ui.test.junit4.createComposeRule
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

class TopBarScreenshotTest : ScreenshotTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderTopBar_Light() {
        composeTestRule.setContent {
            PocketLeagueTheme(
                useDarkTheme = false,
            ) {
                TopBar(title = "Pocket League TopBar")
            }
        }

        compareScreenshot(composeTestRule)
    }

    @Test
    fun renderTopBar_Dark() {
        composeTestRule.setContent {
            PocketLeagueTheme(
                useDarkTheme = true,
            ) {
                TopBar(title = "Pocket League TopBar")
            }
        }

        compareScreenshot(composeTestRule)
    }
}
