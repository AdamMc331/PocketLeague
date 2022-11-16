package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.ui.test.junit4.createComposeRule
import com.adammcneilly.pocketleague.android.designsystem.stats.AnimatableStatComparison
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test

class AnimatableStatComparisonTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Ideally, we may want to introduce Shot library here so we
     * can capture the animation at different points.
     */
    @Test
    fun animateOnAppearance() {
        composeTestRule.mainClock.autoAdvance = false

        composeTestRule.setContent {
            AnimatableStatComparison(
                blueTeamValue = 1,
                orangeTeamValue = 1,
            )
        }

        compareScreenshot(composeTestRule, "start")

        composeTestRule.mainClock.advanceTimeBy(250)
        compareScreenshot(composeTestRule, "quarter")

        composeTestRule.mainClock.advanceTimeBy(250)
        compareScreenshot(composeTestRule, "middle")

        composeTestRule.mainClock.advanceTimeBy(500)
        compareScreenshot(composeTestRule, "end")
    }
}
