package com.adammcneilly.pocketleague.ui

import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

class TopBarScreenshotTest : ScreenshotTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderTopBar() {
        composeTestRule.setContent {
            TopBar(title = "Pocket League TopBar")
        }

        compareScreenshot(composeTestRule)
    }
}
