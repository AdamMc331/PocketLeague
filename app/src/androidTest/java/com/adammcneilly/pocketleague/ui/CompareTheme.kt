package com.adammcneilly.pocketleague.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.adammcneilly.pocketleague.ui.theme.PocketLeagueTheme
import com.karumi.shot.ScreenshotTest

/**
 * Renders a theme that uses light/dark mode based on the [useDarkTheme] parameter, and compares
 * the screenshot.
 */
fun ScreenshotTest.compareTheme(
    composeTestRule: ComposeContentTestRule,
    useDarkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    composeTestRule.setContent {
        PocketLeagueTheme(
            useDarkTheme = useDarkTheme,
        ) {
            content()
        }
    }

    compareScreenshot(composeTestRule)
}
