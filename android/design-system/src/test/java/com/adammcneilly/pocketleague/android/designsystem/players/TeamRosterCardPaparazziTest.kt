package com.adammcneilly.pocketleague.android.designsystem.players

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsBlue
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsOrange
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class TeamRosterCardPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun renderBlueTeamCard() {
        paparazzi.snapshotScreen(useDarkTheme) {
            TeamRosterCard(
                players = listOf(
                    TestDisplayModel.cheese,
                    TestDisplayModel.sosa,
                    TestDisplayModel.zps,
                ),
                teamColor = rlcsBlue,
            )
        }
    }

    @Test
    fun renderOrangeCard() {
        paparazzi.snapshotScreen(useDarkTheme) {
            TeamRosterCard(
                players = listOf(
                    TestDisplayModel.cheese,
                    TestDisplayModel.sosa,
                    TestDisplayModel.zps,
                ),
                teamColor = rlcsOrange,
            )
        }
    }
}
