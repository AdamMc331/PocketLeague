package com.adammcneilly.pocketleague.shared.ui

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.worldChampionship
import org.junit.Rule
import org.junit.Test

class LanEventSummaryCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @Test
    fun renderLanEventCard() {
        paparazzi.snapshotScreen(useDarkTheme = false) {
            LanEventSummaryCard(
                event = EventSummaryDisplayModel.worldChampionship(),
            )
        }
    }
}
