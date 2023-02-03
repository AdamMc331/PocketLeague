package com.adammcneilly.pocketleague.android.designsystem.placeholder

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

/**
 * Contains default values used by [Modifier.placeholder] and [PlaceholderHighlight].
 */
object PlaceholderDefaults {
    /**
     * The default [InfiniteRepeatableSpec] to use for [fade].
     */
    val fadeAnimationSpec: InfiniteRepeatableSpec<Float> by lazy {
        infiniteRepeatable(
            animation = tween(delayMillis = 200, durationMillis = 600),
            repeatMode = RepeatMode.Reverse
        )
    }

    /**
     * The default [InfiniteRepeatableSpec] to use for [shimmer].
     */
    val shimmerAnimationSpec: InfiniteRepeatableSpec<Float> by lazy {
        infiniteRepeatable(
            animation = tween(durationMillis = 1700, delayMillis = 200),
            repeatMode = RepeatMode.Restart
        )
    }

    /**
     * Returns the value used as the the `color` parameter value on [Modifier.placeholder].
     *
     * @param backgroundColor The current background color of the layout. Defaults to
     * `MaterialTheme.colors.surface`.
     * @param contentColor The content color to be used on top of [backgroundColor].
     * @param contentAlpha The alpha component to set on [contentColor] when compositing the color
     * on top of [backgroundColor]. Defaults to `0.1f`.
     */
    @Composable
    fun color(
        backgroundColor: Color = MaterialTheme.colorScheme.surface,
        contentColor: Color = contentColorFor(backgroundColor),
        contentAlpha: Float = 0.1f
    ): Color = contentColor.copy(contentAlpha).compositeOver(backgroundColor)

    /**
     * Returns the value used as the the `highlightColor` parameter value of
     * [PlaceholderHighlight.Companion.fade].
     *
     * @param backgroundColor The current background color of the layout. Defaults to
     * `MaterialTheme.colors.surface`.
     * @param alpha The alpha component to set on [backgroundColor]. Defaults to `0.3f`.
     */
    @Composable
    fun fadeHighlightColor(
        backgroundColor: Color = MaterialTheme.colorScheme.surface,
        alpha: Float = 0.3f
    ): Color = backgroundColor.copy(alpha = alpha)

    /**
     * Returns the value used as the the `highlightColor` parameter value of
     * [PlaceholderHighlight.Companion.shimmer].
     *
     * @param backgroundColor The current background color of the layout. Defaults to
     * `MaterialTheme.colors.surface`.
     * @param alpha The alpha component to set on [backgroundColor]. Defaults to `0.75f`.
     */
    @Composable
    fun shimmerHighlightColor(
        backgroundColor: Color = MaterialTheme.colorScheme.surface,
        alpha: Float = 0.75f
    ): Color {
        return backgroundColor.copy(alpha = alpha)
    }
}
