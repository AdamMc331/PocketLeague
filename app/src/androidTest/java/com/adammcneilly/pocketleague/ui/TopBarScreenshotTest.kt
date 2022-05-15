package com.adammcneilly.pocketleague.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

class TopBarScreenshotTest : ScreenshotTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderTopBar_Light() {
        compareTheme(
            composeTestRule = composeTestRule,
            useDarkTheme = false,
        ) {
            TestTopBar()
        }
    }

    @Test
    fun renderTopBar_Dark() {
        compareTheme(
            composeTestRule = composeTestRule,
            useDarkTheme = true,
        ) {
            TestTopBar()
        }
    }

    @Composable
    private fun TestTopBar() {
        TopBar(title = "Pocket League TopBar")
    }
}
