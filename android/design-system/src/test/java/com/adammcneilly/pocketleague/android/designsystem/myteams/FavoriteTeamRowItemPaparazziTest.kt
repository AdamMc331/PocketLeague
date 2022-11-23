package com.adammcneilly.pocketleague.android.designsystem.myteams

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.TeamOverviewDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class FavoriteTeamRowItemPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderPlaceholder() {
        paparazzi.snapshotScreen(useDarkTheme) {
            FavoriteTeamRowItem(displayModel = TeamOverviewDisplayModel.placeholder)
        }
    }

    @Test
    fun renderItem() {
        paparazzi.snapshotScreen(useDarkTheme) {
            FavoriteTeamRowItem(displayModel = TestDisplayModel.knights)
        }
    }
}
