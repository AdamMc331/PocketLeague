package com.adammcneilly.pocketleague.android.designsystem.components

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class MatchCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderBlueTeamWinner() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
        ) {
            MatchCard(
                match = TestDisplayModel.matchDetailBlueWinner,
                onClick = {},
            )
        }
    }

    @Test
    fun renderOrangeTeamWinner() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
        ) {
            MatchCard(
                match = TestDisplayModel.matchDetailOrangeWinner,
                onClick = {},
            )
        }
    }

    @Test
    fun renderPlaceholder() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
        ) {
            MatchCard(
                match = MatchDetailDisplayModel.placeholder,
                onClick = {},
            )
        }
    }
}
