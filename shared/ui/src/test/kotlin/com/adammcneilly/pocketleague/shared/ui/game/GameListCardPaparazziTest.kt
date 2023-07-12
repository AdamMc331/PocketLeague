package com.adammcneilly.pocketleague.shared.ui.game

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.placeholders
import com.adammcneilly.pocketleague.core.displaymodels.test.variations
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class GameListCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun games() {
        val games = GameDetailDisplayModel.variations()

        paparazzi.snapshotScreen(useDarkTheme) {
            GameListCard(
                games = games,
            )
        }
    }

    @Test
    fun placeholders() {
        val games = GameDetailDisplayModel.placeholders()

        paparazzi.snapshotScreen(useDarkTheme) {
            GameListCard(
                games = games,
            )
        }
    }
}
