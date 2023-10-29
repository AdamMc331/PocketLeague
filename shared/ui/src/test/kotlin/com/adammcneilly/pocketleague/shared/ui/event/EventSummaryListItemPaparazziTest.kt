package com.adammcneilly.pocketleague.shared.ui.event

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.springInvitationalForRegion
import com.adammcneilly.pocketleague.core.displaymodels.test.teamVitality
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class EventSummaryListItemPaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderPlaceholder() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
            screenPaddingDp = 0,
        ) {
            val event = EventSummaryDisplayModel.placeholder

            EventSummaryListItem(
                event = event,
            )
        }
    }

    @Test
    fun renderEventSummaryListItem() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
            screenPaddingDp = 0,
        ) {
            val event = EventSummaryDisplayModel.springInvitationalForRegion("NA")

            EventSummaryListItem(
                event = event,
            )
        }
    }

    @Test
    fun renderWithWinner() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
            screenPaddingDp = 0,
        ) {
            val event = EventSummaryDisplayModel.springInvitationalForRegion("NA")

            EventSummaryListItem(
                event = event.copy(
                    winningTeam = TeamOverviewDisplayModel.teamVitality(),
                ),
            )
        }
    }
}
