package com.adammcneilly.pocketleague.android.designsystem.charts

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.Paparazzi
import com.adammcneilly.pocketleague.android.designsystem.snapshotScreen
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsBlue
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsOrange
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
class PieChartPaparazziTest {

    @get:Rule
    val paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    private val segments = listOf(
        PieChartSegment(10, rlcsBlue),
        PieChartSegment(5, rlcsOrange),
    )

    @Test
    fun renderTwoSegmentsFilled() {
        paparazzi.snapshotScreen(useDarkTheme) {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Fill,
                modifier = Modifier
                    .size(96.dp),
            )
        }
    }

    @Test
    fun renderTwoSegmentsStroked() {
        paparazzi.snapshotScreen(useDarkTheme) {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Stroke(8.dp),
                modifier = Modifier
                    .size(96.dp),
            )
        }
    }

    @Test
    fun renderFilledAndAnimating() {
        paparazzi.snapshotScreen(useDarkTheme) {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Fill,
                percentageToRender = 0.5F,
                modifier = Modifier
                    .size(96.dp),
            )
        }
    }

    @Test
    fun renderStrokedAndAnimating() {
        paparazzi.snapshotScreen(useDarkTheme) {
            PieChart(
                segments = segments,
                segmentStyle = PieChartSegmentStyle.Stroke(8.dp),
                percentageToRender = 0.5F,
                modifier = Modifier
                    .size(96.dp),
            )
        }
    }
}
