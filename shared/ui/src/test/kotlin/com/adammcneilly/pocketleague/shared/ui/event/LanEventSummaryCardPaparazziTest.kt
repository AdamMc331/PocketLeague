package com.adammcneilly.pocketleague.shared.ui.event

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.EventSummaryDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.springMajor
import com.adammcneilly.pocketleague.core.displaymodels.test.teamVitality
import com.adammcneilly.pocketleague.core.displaymodels.test.worldChampionship
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class LanEventSummaryCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderLanEventCard() {
        paparazzi.snapshotScreen(useDarkTheme) {
            LanEventSummaryCard(
                event = EventSummaryDisplayModel.worldChampionship(),
                onEventClicked = {},
            )
        }
    }

    @Test
    fun renderWithWinner() {
        paparazzi.snapshotScreen(useDarkTheme) {
            LanEventSummaryCard(
                event = EventSummaryDisplayModel.springMajor().copy(
                    winningTeam = TeamOverviewDisplayModel.teamVitality(),
                ),
                onEventClicked = {},
            )
        }
    }
}
