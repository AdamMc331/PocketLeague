package com.adammcneilly.pocketleague.shared.ui

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class LanEventCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun renderLanEventCard() {
        paparazzi.snapshotScreen(useDarkTheme = false) {
            LanEventCard()
        }
    }
}
