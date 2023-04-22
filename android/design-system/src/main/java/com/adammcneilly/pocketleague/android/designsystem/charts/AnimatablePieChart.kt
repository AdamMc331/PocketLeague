package com.adammcneilly.pocketleague.android.designsystem.charts

import android.content.res.Configuration
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adammcneilly.pocketleague.android.designsystem.theme.PocketLeagueTheme
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsBlue
import com.adammcneilly.pocketleague.android.designsystem.theme.rlcsOrange
import com.adammcneilly.pocketleague.android.designsystem.utils.whenInView
import kotlinx.coroutines.launch

/**
 * This is a wrapper around [PieChart] that triggers an animation when the
 * pie chart comes into view.
 */
@Composable
fun AnimatablePieChart(
    segments: List<PieChartSegment>,
    modifier: Modifier = Modifier,
    segmentStyle: PieChartSegmentStyle = PieChartSegmentStyle.Fill,
) {
    val animationPercentage = remember {
        AnimationState(0F)
    }

    val coroutineScope = rememberCoroutineScope()

    PieChart(
        segments = segments,
        percentageToRender = animationPercentage.value,
        segmentStyle = segmentStyle,
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

@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun AnimatablePieChartPreview() {
    val segments = listOf(
        PieChartSegment(7, rlcsBlue),
        PieChartSegment(10, rlcsOrange),
        PieChartSegment(2, Color.Gray),
    )

    PocketLeagueTheme {
        Surface {
            AnimatablePieChart(
                segments = segments,
                modifier = Modifier
                    .padding(16.dp)
                    .size(96.dp),
            )
        }
    }
}
