package com.adammcneilly.pocketleague.shared.ui

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.springInvitationalForAllRegions
import org.junit.Rule
import org.junit.Test

class EventSummaryListCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun renderEventSummaryListCard() {
        paparazzi.snapshotScreen(false) {
            val events = EventSummaryDisplayModel.springInvitationalForAllRegions()

            EventSummaryListCard(
                events = events,
            )
        }
    }
}
