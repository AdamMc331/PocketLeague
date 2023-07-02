package com.adammcneilly.pocketleague.shared.ui

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.springInvitationalForRegion
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
            val event = EventSummaryDisplayModel.springInvitationalForRegion("NA")

            EventSummaryListItem(
                event = event,
            )
        }
    }
}
