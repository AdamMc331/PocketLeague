package com.adammcneilly.pocketleague.shared.ui.stats

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class CoreStatsComparisonCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderDefaultCard() {
        paparazzi.snapshotScreen(useDarkTheme) {
            CoreStatsComparisonCard(
                blueTeamStats = TestDisplayModel.coreStats,
                orangeTeamStats = TestDisplayModel.coreStats,
            )
        }
    }
}
