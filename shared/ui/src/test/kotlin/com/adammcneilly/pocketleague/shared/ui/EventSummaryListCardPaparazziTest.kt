package com.adammcneilly.pocketleague.shared.ui

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class EventSummaryListCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun renderEventSummaryListCard() {
        paparazzi.snapshotScreen(false) {
            EventSummaryListCard()
        }
    }
}
