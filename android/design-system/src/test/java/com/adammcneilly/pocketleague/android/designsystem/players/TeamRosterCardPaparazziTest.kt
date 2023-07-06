package com.adammcneilly.pocketleague.android.designsystem.players

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.core.displaymodels.test.TestDisplayModel
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsBlue
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsOrange
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

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
                useDarkTheme = useDarkTheme,
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
                useDarkTheme = useDarkTheme,
            )
        }
    }
}
