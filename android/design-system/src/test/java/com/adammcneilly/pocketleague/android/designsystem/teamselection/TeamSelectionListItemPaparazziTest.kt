package com.adammcneilly.pocketleague.android.designsystem.teamselection

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class TeamSelectionListItemPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderFavoriteTeam() {
        paparazzi.snapshotScreen(useDarkTheme) {
            TeamSelectionListItem(
                team = TestDisplayModel.knights,
                isFavorite = true,
                onFavoriteChanged = {},
            )
        }
    }

    @Test
    fun renderNotFavoriteTeam() {
        paparazzi.snapshotScreen(useDarkTheme) {
            TeamSelectionListItem(
                team = TestDisplayModel.knights,
                isFavorite = false,
                onFavoriteChanged = {},
            )
        }
    }
}
