package com.adammcneilly.pocketleague.android.design

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp

private const val COMPACT_WIDTH_RATIO = 1F
private const val MEDIUM_WIDTH_RATIO = 0.75F
private const val EXPANDED_WIDTH_RATIO = 0.5F

private const val MIN_MEDIUM_WIDTH_DP = 600
private const val MIN_EXPANDED_WIDTH_DP = 840

/**
 * This is a custom modifier, that will change the width of a Composable, based on the amount of space
 * available to it. Centering the Composable within that available space as well.
 *
 * If the available space is within the COMPACT bucket, we will fill the maximum width.
 * If the available space is within the MEDIUM bucket, we will 75% of the maximum width.
 * If the available space is within the EXPANDED bucket, we will fill 50% of the maximum width.
 */
fun Modifier.adaptiveWidth(
    compactWidthRatio: Float = COMPACT_WIDTH_RATIO,
    mediumWidthRatio: Float = MEDIUM_WIDTH_RATIO,
    expandedWidthRatio: Float = EXPANDED_WIDTH_RATIO,
) = this
    .layout { measurable, constraints ->
        val widthRatio = when {
            constraints.maxWidth < MIN_MEDIUM_WIDTH_DP.dp.toPx() -> compactWidthRatio
            constraints.maxWidth < MIN_EXPANDED_WIDTH_DP.dp.toPx() -> mediumWidthRatio
            else -> expandedWidthRatio
        }

        val widthToUse = constraints.maxWidth * widthRatio

        val newConstraints = constraints.copy(
            minWidth = widthToUse.toInt(),
            maxWidth = widthToUse.toInt(),
        )

        val placeable = measurable.measure(newConstraints)

        val x = Alignment.CenterHorizontally.align(
            size = placeable.width,
            space = constraints.maxWidth,
            layoutDirection = layoutDirection,
        )

        layout(constraints.maxWidth, placeable.height) {
            placeable.place(x = x, y = 0)
        }
    }
