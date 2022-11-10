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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsBlue
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsOrange
import com.adammcneilly.pocketleague.android.designsystem.utils.whenInView
import kotlinx.coroutines.launch

// Creates a Semantics property of type boolean
val PercentageAnimatedKey = SemanticsPropertyKey<Float>("PercentageAnimated")
var SemanticsPropertyReceiver.percentageAnimated by PercentageAnimatedKey

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
            }
            .semantics {
                percentageAnimated = animationPercentage.value
                testTag = "stat_comparison"
            }
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
    val dividerColor = LocalContentColor.current

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        val midHeight = size.height.div(2)
        val totalValue = blueTeamValue.plus(orangeTeamValue)
        val blueTeamPercentage = blueTeamValue.toFloat().div(totalValue)
        val dividingPoint = size.width.times(blueTeamPercentage)
        val lineWidth = 4.dp.toPx()

        drawBlueLine(midHeight, dividingPoint, lineWidth, percentageToRender)
        drawOrangeLine(dividingPoint, midHeight, lineWidth, percentageToRender)
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
            AnimatableStatComparison(
                blueTeamValue = 7,
                orangeTeamValue = 1,
                modifier = Modifier
                    .padding(16.dp),
            )
        }
    }
}
