package com.adammcneilly.pocketleague.composables.android.test

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.composables.bars.TopBar
import org.junit.Rule
import org.junit.Test

class PaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun renderTopBar() {
        paparazzi.snapshot {
            TopBar(
                title = "Test Title",
            )
        }
    }
}
