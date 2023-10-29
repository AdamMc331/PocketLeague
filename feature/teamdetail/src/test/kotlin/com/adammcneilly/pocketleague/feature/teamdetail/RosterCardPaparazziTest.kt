package com.adammcneilly.pocketleague.feature.teamdetail

import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.core.displaymodels.PlayerDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.moistRoster
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class RosterCardPaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun placeholders() {
        val roster = listOf(
            PlayerDisplayModel.placeholder,
            PlayerDisplayModel.placeholder,
            PlayerDisplayModel.placeholder,
        )

        paparazzi.snapshotScreen(useDarkTheme) {
            RosterCard(roster)
        }
    }

    @Test
    fun withCoachAndSub() {
        val roster = PlayerDisplayModel.moistRoster()

        paparazzi.snapshotScreen(useDarkTheme) {
            RosterCard(roster)
        }
    }
}
