package com.adammcneilly.pocketleague.shared.ui.stats

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.shared.ui.snapshotScreen
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class StatComparisonPaparazziTest {
    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    @Test
    fun blueTeamLeadingStat() {
        paparazzi.snapshotScreen(useDarkTheme) {
            StatComparison(
                blueTeamValue = 3,
                orangeTeamValue = 1,
                percentageToRender = 1F,
                modifier =
                    Modifier
                        .padding(16.dp),
            )
        }
    }

    @Test
    fun orangeTeamLeadingStat() {
        paparazzi.snapshotScreen(useDarkTheme) {
            StatComparison(
                blueTeamValue = 1,
                orangeTeamValue = 3,
                percentageToRender = 1F,
                modifier =
                    Modifier
                        .padding(16.dp),
            )
        }
    }

    @Test
    fun renderCompleteStatComparison() {
        paparazzi.snapshotScreen(useDarkTheme) {
            StatComparison(
                blueTeamValue = 1,
                orangeTeamValue = 1,
                percentageToRender = 1F,
                modifier =
                    Modifier
                        .padding(16.dp),
            )
        }
    }

    @Test
    fun renderHalfwayStatComparison() {
        paparazzi.snapshotScreen(useDarkTheme) {
            StatComparison(
                blueTeamValue = 1,
                orangeTeamValue = 1,
                percentageToRender = 0.5F,
                modifier =
                    Modifier
                        .padding(16.dp),
            )
        }
    }

    @Test
    fun renderQuarterStatComparison() {
        paparazzi.snapshotScreen(useDarkTheme) {
            StatComparison(
                blueTeamValue = 1,
                orangeTeamValue = 1,
                percentageToRender = 0.25F,
                modifier =
                    Modifier
                        .padding(16.dp),
            )
        }
    }
}
