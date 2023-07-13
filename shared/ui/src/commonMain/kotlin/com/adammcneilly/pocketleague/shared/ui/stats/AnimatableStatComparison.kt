package com.adammcneilly.pocketleague.shared.ui.stats

import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsBlue
import com.adammcneilly.pocketleague.shared.design.system.theme.rlcsOrange
import com.adammcneilly.pocketleague.shared.ui.utils.whenInView
import kotlinx.coroutines.launch

/**
 * Shows the comparison between [blueTeamValue] and [orangeTeamValue] by drawing lines on a canvas.
 */
@Composable
fun AnimatableStatComparison(
    blueTeamValue: Int,
    orangeTeamValue: Int,
    modifier: Modifier = Modifier,
) {
    val animationPercentage = remember {
        AnimationState(0F)
    }

    val coroutineScope = rememberCoroutineScope()

    StatComparison(
        blueTeamValue = blueTeamValue,
        orangeTeamValue = orangeTeamValue,
        percentageToRender = animationPercentage.value,
        modifier = modifier
            .whenInView {
                coroutineScope.launch {
                    animationPercentage.animateTo(
                        targetValue = 1F,
                        animationSpec = tween(
                            durationMillis = 1000,
                        ),
                    )
                }
            },
    )
}

/**
 * Unlike [AnimatableStatComparison], this is a stateless way to render
 * a comparison between a [blueTeamValue] and [orangeTeamValue].
 *
 * If a caller doesn't care about animating a stat comparison, they can use this
 * composable directly and keep the default [percentageToRender] as 1.
 */
@Composable
fun StatComparison(
    blueTeamValue: Int,
    orangeTeamValue: Int,
    modifier: Modifier = Modifier,
    percentageToRender: Float = 1F,
) {
    // Divider color should be the color of the team leading in this statistic
    // or the local content color if they tied.
//    val dividerColor = when {
//        blueTeamValue > orangeTeamValue -> rlcsBlue
//        orangeTeamValue > blueTeamValue -> rlcsOrange
//        else -> LocalContentColor.current
//    }
    val dividerColor = LocalContentColor.current

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
        val leadingLineWidth = lineWidth * 1.5F

        val blueLineWidth = if (blueTeamValue > orangeTeamValue) {
            leadingLineWidth
        } else {
            lineWidth
        }

        val orangeLineWidth = if (orangeTeamValue > blueTeamValue) {
            leadingLineWidth
        } else {
            lineWidth
        }

        drawBlueLine(midHeight, dividingPoint, blueLineWidth, percentageToRender)
        drawOrangeLine(dividingPoint, midHeight, orangeLineWidth, percentageToRender)
        drawDivider(dividingPoint, midHeight, lineWidth, dividerColor, percentageToRender)
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
