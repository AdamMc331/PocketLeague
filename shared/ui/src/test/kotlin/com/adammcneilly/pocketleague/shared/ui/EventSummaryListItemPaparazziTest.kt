package com.adammcneilly.pocketleague.shared.ui

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class EventSummaryListItemPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun renderEventSummaryListItem() {
        paparazzi.snapshotScreen(
            useDarkTheme = false,
            screenPaddingDp = 0,
        ) {
            EventSummaryListItem()
        }
    }
}
