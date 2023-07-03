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
class MatchCarouselPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderSingleItemCarousel() {
        val matchList = listOf(TestDisplayModel.matchDetailBlueWinner)

        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
            screenPaddingDp = 0,
        ) {
            MatchCarousel(matchList) {}
        }
    }

    @Test
    fun renderMultiItemCarousel() {
        val matchList = listOf(
            TestDisplayModel.matchDetailBlueWinner,
            TestDisplayModel.matchDetailBlueWinner,
            TestDisplayModel.matchDetailBlueWinner,
        )

        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
            screenPaddingDp = 0,
        ) {
            MatchCarousel(matchList) {}
        }
    }
}
