package com.adammcneilly.pocketleague.android.designsystem.game

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.testBlueWinningGameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.testOrangeWinningGameDetailDisplayModel
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class GameListItemPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderBlueTeamWinner() {
        paparazzi.snapshotScreen(useDarkTheme) {
            GameListItem(displayModel = testBlueWinningGameDetailDisplayModel)
        }
    }

    @Test
    fun renderOrangeTeamWinner() {
        paparazzi.snapshotScreen(useDarkTheme) {
            GameListItem(displayModel = testOrangeWinningGameDetailDisplayModel)
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
                displayModel = testBlueWinningGameDetailDisplayModel.copy(
                    otLabel = "OT +1:23",
                ),
            )
        }
    }
}
