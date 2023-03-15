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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsBlue
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsOrange

@Composable
fun PieChart(
    segments: List<PieChartSegment>,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp,
) {
    Canvas(
        modifier = modifier,
    ) {
        val fullCircleAngle = 360F
        val strokeWidthPx = strokeWidth.toPx()
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
            val sweepAngle = (segmentPercentage * fullCircleAngle)

            drawSegment(
                startAngle = currentStartAngle,
                sweepAngle = sweepAngle,
                color = segment.color,
                strokeWidthPx = strokeWidthPx,
            )

            currentStartAngle += sweepAngle
        }
    }
}

private fun DrawScope.drawSegment(
    startAngle: Float,
    sweepAngle: Float,
    color: Color,
    strokeWidthPx: Float,
) {
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = strokeWidthPx,
        ),
    )
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
