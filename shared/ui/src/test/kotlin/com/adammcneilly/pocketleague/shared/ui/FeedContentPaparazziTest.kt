package com.adammcneilly.pocketleague.shared.ui

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import kotlin.test.Test

class FeedContentPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun renderFeedContent() {
        paparazzi.snapshotScreen(
            useDarkTheme = false,
            screenPaddingDp = 0,
        ) {
            FeedContent()
        }
    }
}
