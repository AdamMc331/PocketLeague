package com.adammcneilly.pocketleague.shared.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalMaterial3Api::class)
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
