package com.adammcneilly.pocketleague

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    @Ignore("Need to differentiate text values for recent matches & ongoing events from test data. But since this test doesn't really do anything yet I'm just ignoring for now.")
    fun renderFeedScreen() {
        composeTestRule
            .onNodeWithText("RLCS 2021-22 World Championship")
            .assertIsDisplayed()
    }
}
