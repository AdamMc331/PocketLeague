package com.adammcneilly.pocketleague.android.designsystem.stats

import android.content.res.Configuration
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsBlue
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsOrange

/**
 * Shows the comparison between [blueTeamValue] and [orangeTeamValue] by drawing lines on a canvas.
 */
@Composable
fun StatComparison(
    blueTeamValue: Int,
    orangeTeamValue: Int,
    modifier: Modifier = Modifier,
) {
    val dividerColor = LocalContentColor.current

    val animationPercentage = remember {
        AnimationState(0F)
    }

    LaunchedEffect(Unit) {
        animationPercentage.animateTo(
            targetValue = 1F,
            animationSpec = tween(
                durationMillis = 1000,
            ),
        )
    }

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

        drawBlueLine(midHeight, dividingPoint, lineWidth, animationPercentage.value)
        drawOrangeLine(dividingPoint, midHeight, lineWidth, animationPercentage.value)
        drawDivider(dividingPoint, midHeight, lineWidth, dividerColor, animationPercentage.value)
    }
}

private fun DrawScope.drawDivider(
    dividingPoint: Float,
    midHeight: Float,
    lineWidth: Float,
    dividerColor: Color,
    animationPercentage: Float,
) {
    val totalDividerOffset = lineWidth.times(2)
    val dividerOffset = totalDividerOffset * animationPercentage

    drawLine(
        color = dividerColor,
        start = Offset(
            x = dividingPoint,
            y = midHeight.minus(dividerOffset),
        ),
        end = Offset(
            x = dividingPoint,
            y = midHeight.plus(dividerOffset),
        ),
        strokeWidth = lineWidth,
    )
}

private fun DrawScope.drawOrangeLine(
    dividingPoint: Float,
    midHeight: Float,
    lineWidth: Float,
    animationPercentage: Float,
) {
    val totalLength = (size.width - dividingPoint)
    val lengthToRender = totalLength * animationPercentage
    val endingX = dividingPoint + lengthToRender

    drawLine(
        color = rlcsOrange,
        start = Offset(
            x = dividingPoint,
            y = midHeight,
        ),
        end = Offset(
            x = endingX,
            y = midHeight,
        ),
        strokeWidth = lineWidth,
    )
}

private fun DrawScope.drawBlueLine(
    midHeight: Float,
    dividingPoint: Float,
    lineWidth: Float,
    animationPercentage: Float,
) {
    val lineLengthToRender = animationPercentage * dividingPoint
    val startingX = (dividingPoint - lineLengthToRender)

    drawLine(
        color = rlcsBlue,
        start = Offset(
            x = startingX,
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
