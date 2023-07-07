package com.adammcneilly.pocketleague.shared.ui.game

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class GameListItemPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderBlueTeamWinner() {
        paparazzi.snapshotScreen(useDarkTheme) {
            GameListItem(displayModel = TestDisplayModel.gameDetailBlueWinner)
        }
    }

    @Test
    fun renderOrangeTeamWinner() {
        paparazzi.snapshotScreen(useDarkTheme) {
            GameListItem(displayModel = TestDisplayModel.gameDetailOrangeWinner)
        }
    }

    @Test
    fun renderPlaceholder() {
        paparazzi.snapshotScreen(useDarkTheme) {
            GameListItem(displayModel = GameDetailDisplayModel.placeholder)
        }
    }

    @Test
    fun renderWithOT() {
        paparazzi.snapshotScreen(useDarkTheme) {
            GameListItem(
                displayModel = TestDisplayModel.gameDetailBlueWinner.copy(
                    otLabel = "OT +1:23",
                ),
            )
        }
    }

    @Test
    fun renderWithLongMapName() {
        paparazzi.snapshotScreen(useDarkTheme) {
            GameListItem(
                displayModel = TestDisplayModel.gameDetailBlueWinner.copy(
                    map = "Utopia Coliseum (Standard, Night)",
                ),
            )
        }
    }
}
