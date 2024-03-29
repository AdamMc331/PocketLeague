package com.adammcneilly.pocketleague.shared.ui.stats

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.shared.ui.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.shared.ui.theme.rlcsBlue
import com.adammcneilly.pocketleague.shared.ui.theme.rlcsOrange
import com.adammcneilly.pocketleague.shared.ui.utils.whenInView
import kotlinx.coroutines.launch

/**
 * For the stat line of the team leading in a given statistic, multiply the thickness
 * of that line by this scale, so it can be emphasized against the other team.
 */
private const val LEADING_TEAM_STAT_WIDTH_SCALE = 1.5F

/**
 * Shows the comparison between [blueTeamValue] and [orangeTeamValue] by drawing lines on a canvas.
 */
@Composable
fun AnimatableStatComparison(
    blueTeamValue: Int,
    orangeTeamValue: Int,
    modifier: Modifier = Modifier,
    initialAnimationPercentage: Float = 0F,
    showValues: Boolean = false,
) {
    val animationPercentage = remember {
        AnimationState(initialAnimationPercentage)
    }

    val coroutineScope = rememberCoroutineScope()

    StatComparison(
        blueTeamValue = blueTeamValue,
        orangeTeamValue = orangeTeamValue,
        percentageToRender = animationPercentage.value,
        showValues = showValues,
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
    showValues: Boolean = false,
) {
    Row(
        modifier = modifier
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AnimatedVisibility(
            visible = showValues,
        ) {
            Text(
                text = blueTeamValue.toString(),
                modifier = Modifier
                    .padding(end = PocketLeagueTheme.sizes.cardPadding),
            )
        }

        StatComparisonLines(
            blueTeamValue = blueTeamValue,
            orangeTeamValue = orangeTeamValue,
            percentageToRender = percentageToRender,
            modifier = Modifier
                .weight(1F),
        )

        AnimatedVisibility(
            visible = showValues,
        ) {
            Text(
                text = orangeTeamValue.toString(),
                modifier = Modifier
                    .padding(start = PocketLeagueTheme.sizes.cardPadding),
            )
        }
    }
}

@Composable
private fun StatComparisonLines(
    blueTeamValue: Int,
    orangeTeamValue: Int,
    percentageToRender: Float,
    modifier: Modifier = Modifier,
) {
    val dividerColor = LocalContentColor.current

    Canvas(
        modifier = modifier,
    ) {
        val midHeight = size.height.div(2)
        val totalValue = blueTeamValue.plus(orangeTeamValue)
        val blueTeamPercentage = blueTeamValue.toFloat().div(totalValue)
        val dividingPoint = size.width.times(blueTeamPercentage)
        val lineWidth = 4.dp.toPx()
        val leadingLineWidth = lineWidth * LEADING_TEAM_STAT_WIDTH_SCALE

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
