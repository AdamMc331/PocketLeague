package com.adammcneilly.pocketleague.android.designsystem.stats

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsBlue
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsOrange

@Composable
fun StatComparison(
    blueTeamValue: Int,
    orangeTeamValue: Int,
    modifier: Modifier = Modifier,
) {
    val dividerColor = MaterialTheme.colorScheme.onSurface

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
    ) {
        val midHeight = size.height.div(2)
        val totalValue = blueTeamValue.plus(orangeTeamValue)
        val blueTeamPercentage = blueTeamValue.toFloat().div(totalValue)
        val dividingPoint = size.width.times(blueTeamPercentage)
        val lineWidth = 4.dp.toPx()

        drawBlueLine(midHeight, dividingPoint, lineWidth)
        drawOrangeLine(dividingPoint, midHeight, lineWidth)
        drawDivider(dividingPoint, midHeight, lineWidth, dividerColor)
    }
}

private fun DrawScope.drawDivider(
    dividingPoint: Float,
    midHeight: Float,
    lineWidth: Float,
    dividerColor: Color,
) {
    val dividerOffsetPx = lineWidth.times(2)

    drawLine(
        color = dividerColor,
        start = Offset(
            x = dividingPoint,
            y = midHeight.minus(dividerOffsetPx),
        ),
        end = Offset(
            x = dividingPoint,
            y = midHeight.plus(dividerOffsetPx),
        ),
        strokeWidth = lineWidth,
    )
}

private fun DrawScope.drawOrangeLine(
    dividingPoint: Float,
    midHeight: Float,
    lineWidth: Float,
) {
    drawLine(
        color = rlcsOrange,
        start = Offset(
            x = dividingPoint,
            y = midHeight,
        ),
        end = Offset(
            x = size.width,
            y = midHeight,
        ),
        strokeWidth = lineWidth,
    )
}

private fun DrawScope.drawBlueLine(
    midHeight: Float,
    dividingPoint: Float,
    lineWidth: Float,
) {
    drawLine(
        color = rlcsBlue,
        start = Offset(
            x = 0F,
            y = midHeight,
        ),
        end = Offset(
            x = dividingPoint,
            y = midHeight,
        ),
        strokeWidth = lineWidth,
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun StatComparisonPreview() {
    PocketLeagueTheme {
        Surface {
            StatComparison(
                blueTeamValue = 7,
                orangeTeamValue = 1,
                modifier = Modifier
                    .padding(16.dp),
            )
        }
    }
}
