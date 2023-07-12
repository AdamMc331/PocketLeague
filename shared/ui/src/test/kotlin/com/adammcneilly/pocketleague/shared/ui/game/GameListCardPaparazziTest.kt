package com.adammcneilly.pocketleague.shared.ui.game

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.blueWinner
import com.adammcneilly.pocketleague.core.displaymodels.test.orangeWinner
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
    fun render() {
        val games = listOf(
            GameDetailDisplayModel.blueWinner(),
            GameDetailDisplayModel.orangeWinner(),
            GameDetailDisplayModel.orangeWinner().copy(
                otLabel = "OT +12:02",
            ),
        )

        paparazzi.snapshotScreen(useDarkTheme) {
            GameListCard(
                games = games,
            )
        }
    }
}
