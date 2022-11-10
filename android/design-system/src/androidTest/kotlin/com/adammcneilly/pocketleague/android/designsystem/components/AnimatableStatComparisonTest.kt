package com.adammcneilly.pocketleague.android.designsystem.components

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.adammcneilly.pocketleague.android.designsystem.stats.AnimatableStatComparison
import com.adammcneilly.pocketleague.android.designsystem.stats.PercentageAnimatedKey
import org.junit.Rule
import org.junit.Test

class AnimatableStatComparisonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun animateOnAppearance() {
        composeTestRule.mainClock.autoAdvance = false

        composeTestRule.setContent {
            AnimatableStatComparison(
                blueTeamValue = 1,
                orangeTeamValue = 1,
            )
        }

        composeTestRule
            .onNodeWithTag("stat_comparison")
            .assert(SemanticsMatcher.expectValue(PercentageAnimatedKey, 0F))

        composeTestRule.mainClock.advanceTimeBy(500)

        // Because this uses a tween animation, at the "halfway point" we'll actually
        // have animated a little further than that. This key comes from some trial and error
        // but it serves its purpose in making sure we don't change the animation
        // without breaking this test.
        composeTestRule
            .onNodeWithTag("stat_comparison")
            .assert(SemanticsMatcher.expectValue(PercentageAnimatedKey, 0.7705798F))

        composeTestRule.mainClock.advanceTimeBy(500)

        composeTestRule
            .onNodeWithTag("stat_comparison")
            .assert(SemanticsMatcher.expectValue(PercentageAnimatedKey, 1F))
    }
}
