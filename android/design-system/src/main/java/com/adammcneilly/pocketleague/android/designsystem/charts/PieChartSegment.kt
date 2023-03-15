package com.adammcneilly.pocketleague.android.designsystem.charts

import androidx.compose.ui.graphics.Color

/**
 * This data class is used to render a portion of a [PieChart], with some [value] and a [color].
 *
 * The [PieChart] composable will be responsible for calculating the actual lengths of these segments.
 *
 * @property[value] This represents the integer value of this segment, as it relates to the other segments.
 * For example, if a pie chart is used to represent a win loss ratio, and a team has 10 wins, 5 losses, then
 * there will be one segment with a value of 10, and one segment with a value of 5.
 * @property[color] The compose Color used to represent this segment in the completed chart.
 */
data class PieChartSegment(
    val value: Int,
    val color: Color,
)
