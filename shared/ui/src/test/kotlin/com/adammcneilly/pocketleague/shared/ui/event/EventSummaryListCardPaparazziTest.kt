package com.adammcneilly.pocketleague.shared.ui.event

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.springInvitationalForAllRegions
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class EventSummaryListCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderEventSummaryListCard() {
        paparazzi.snapshotScreen(useDarkTheme) {
            val events = EventSummaryDisplayModel.springInvitationalForAllRegions()

            EventSummaryListCard(
                events = events,
            )
        }
    }
}
