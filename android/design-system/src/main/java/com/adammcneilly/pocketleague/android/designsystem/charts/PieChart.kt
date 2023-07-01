package com.adammcneilly.pocketleague.android.designsystem.charts

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.design.system.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsBlue
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsOrange

private const val FULL_CIRCLE_ANGLE = 360F

/**
 * Renders each of the supplied [segments] in a pie chart format.
 *
 * If you want to animate this component, reference [AnimatablePieChart].
 */
@Composable
fun PieChart(
    segments: List<PieChartSegment>,
    modifier: Modifier = Modifier,
    segmentStyle: PieChartSegmentStyle = PieChartSegmentStyle.Fill,
    percentageToRender: Float = 1F,
) {
    Canvas(
        modifier = modifier,
    ) {
        var currentStartAngle = 0F
        // We need to sum all values, so we can get the percentage a segment
        // takes compared to everything. For example, 10 wins and 5 losses
        // means each segment should be determined based on its relationship
        // to all 15 games.
        val sumValues = segments.sumOf { segment ->
            segment.value
        }.toFloat()

        segments.forEach { segment ->
            val segmentPercentage = segment.value / sumValues
            val totalSweepAngle = (segmentPercentage * FULL_CIRCLE_ANGLE)

            val sweepAngleToRender = totalSweepAngle * percentageToRender

            drawSegment(
                startAngle = currentStartAngle,
                sweepAngle = sweepAngleToRender,
                color = segment.color,
                style = segmentStyle,
            )

            currentStartAngle += totalSweepAngle
        }
    }
}

private fun DrawScope.drawSegment(
    startAngle: Float,
    sweepAngle: Float,
    color: Color,
    style: PieChartSegmentStyle,
) {
    val drawStyle = when (style) {
        PieChartSegmentStyle.Fill -> {
            Fill
        }
        is PieChartSegmentStyle.Stroke -> {
            Stroke(
                width = style.strokeWidthDp.toPx(),
            )
        }
    }

    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = (style == PieChartSegmentStyle.Fill),
        style = drawStyle,
    )
}

/**
 * Defines the different ways that a segment can be rendered inside a pie chart.
 */
sealed class PieChartSegmentStyle {
    /**
     * If used, the pie chart segment will be completely filled and connected into
     * the center of the circle.
     */
    object Fill : PieChartSegmentStyle()

    /**
     * If used, the segments of the pie chart will be a stroke with empty space inside the circle.
     */
    data class Stroke(
        val strokeWidthDp: Dp,
    ) : PieChartSegmentStyle()
}

@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PieChartTwoSegmentPreview() {
    val segments = listOf(
        PieChartSegment(7, rlcsBlue),
        PieChartSegment(10, rlcsOrange),
    )

    PocketLeagueTheme {
        Surface {
            PieChart(
                segments = segments,
                modifier = Modifier
                    .padding(16.dp)
                    .size(96.dp),
            )
        }
    }
}

@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PieChartThreeSegmentPreview() {
    val segments = listOf(
        PieChartSegment(7, rlcsBlue),
        PieChartSegment(10, rlcsOrange),
        PieChartSegment(2, Color.Gray),
    )

    PocketLeagueTheme {
        Surface {
            PieChart(
                segments = segments,
                modifier = Modifier
                    .padding(16.dp)
                    .size(96.dp),
            )
        }
    }
}

@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PieChartThreeSegmentStrokePreview() {
    val segments = listOf(
        PieChartSegment(7, rlcsBlue),
        PieChartSegment(10, rlcsOrange),
        PieChartSegment(2, Color.Gray),
    )

    PocketLeagueTheme {
        Surface {
            PieChart(
                segments = segments,
                modifier = Modifier
                    .padding(16.dp)
                    .size(96.dp),
                segmentStyle = PieChartSegmentStyle.Stroke(8.dp),
            )
        }
    }
}

@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun PieChartHalfwayRendered() {
    val segments = listOf(
        PieChartSegment(7, rlcsBlue),
        PieChartSegment(10, rlcsOrange),
        PieChartSegment(2, Color.Gray),
    )

    PocketLeagueTheme {
        Surface {
            PieChart(
                segments = segments,
                modifier = Modifier
                    .padding(16.dp)
                    .size(96.dp),
                percentageToRender = 0.5F,
            )
        }
    }
}
