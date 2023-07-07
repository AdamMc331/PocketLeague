package com.adammcneilly.pocketleague.shared.ui.match

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class MatchCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderBlueTeamWinner() {
        paparazzi.snapshotScreen(useDarkTheme) {
            MatchCard(
                match = TestDisplayModel.matchDetailBlueWinner,
                onClick = {},
            )
        }
    }

    @Test
    fun renderOrangeTeamWinner() {
        paparazzi.snapshotScreen(useDarkTheme) {
            MatchCard(
                match = TestDisplayModel.matchDetailOrangeWinner,
                onClick = {},
            )
        }
    }
}
