package com.adammcneilly.pocketleague.android.designsystem.matches

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class MatchDetailHeaderPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderMatchDetailHeader() {
        paparazzi.snapshotScreen(useDarkTheme) {
            MatchDetailHeader(
                displayModel = TestDisplayModel.matchDetailBlueWinner,
            )
        }
    }

    @Test
    fun renderPlaceholder() {
        paparazzi.snapshotScreen(useDarkTheme) {
            MatchDetailHeader(
                displayModel = MatchDetailDisplayModel.placeholder,
            )
        }
    }
}
