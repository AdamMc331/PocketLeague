package com.adammcneilly.pocketleague.android.designsystem.utils

import androidx.compose.ui.graphics.Color

private const val DARKEN_COLOR_RATIO = 0.75F

/**
 * Darkens this color by applying the given [ratio] to each of the RGB components
 * of this color.
 */
fun Color.darken(
    ratio: Float = DARKEN_COLOR_RATIO
): Color {
    return Color(
        red = this.red * ratio,
        green = this.green * ratio,
        blue = this.blue * ratio
    )
}
