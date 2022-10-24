package com.adammcneilly.pocketleague.android.designsystem.game

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.blueWinner
import com.adammcneilly.pocketleague.core.displaymodels.test.orangeWinner
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
            GameListItem(displayModel = GameDetailDisplayModel.blueWinner)
        }
    }

    @Test
    fun renderOrangeTeamWinner() {
        paparazzi.snapshotScreen(useDarkTheme) {
            GameListItem(displayModel = GameDetailDisplayModel.orangeWinner)
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
                displayModel = GameDetailDisplayModel.blueWinner.copy(
                    otLabel = "OT +1:23",
                ),
            )
        }
    }
}
