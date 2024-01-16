package com.adammcneilly.pocketleague.shared.ui.swiss

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel.eliminatedSwiss
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel.inProgressSwiss
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel.qualifiedSwiss
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class SwissTeamResultListItemPaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderQualified() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
            screenPaddingDp = 0,
        ) {
            SwissTeamResultListItem(
                displayModel = qualifiedSwiss,
            )
        }
    }

    @Test
    fun renderInProgress() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
            screenPaddingDp = 0,
        ) {
            SwissTeamResultListItem(
                displayModel = inProgressSwiss,
            )
        }
    }

    @Test
    fun renderEliminated() {
        paparazzi.snapshotScreen(
            useDarkTheme = useDarkTheme,
            screenPaddingDp = 0,
        ) {
            SwissTeamResultListItem(
                displayModel = eliminatedSwiss,
            )
        }
    }
}
