package com.adammcneilly.pocketleague.feature.eventdetail

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class StageMatchListItemPaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun placeholder() {
        paparazzi.snapshotScreen(useDarkTheme) {
            StageMatchListItem(MatchDetailDisplayModel.placeholder)
        }
    }

    @Test
    fun blueWinner() {
        paparazzi.snapshotScreen(useDarkTheme) {
            StageMatchListItem(TestDisplayModel.matchDetailBlueWinner)
        }
    }

    @Test
    fun orangewinner() {
        paparazzi.snapshotScreen(useDarkTheme) {
            StageMatchListItem(TestDisplayModel.matchDetailOrangeWinner)
        }
    }
}
