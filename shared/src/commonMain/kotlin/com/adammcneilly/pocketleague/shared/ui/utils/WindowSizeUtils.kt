package com.adammcneilly.pocketleague.shared.ui.utils

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.window.core.layout.WindowSizeClass

/**
 * Access the current [androidx.compose.material3.adaptive.WindowAdaptiveInfo] to determine if it's at
 * a medium or wider width class. This is used to determine whether
 * we should show a bottom navigation or a navigation rail.
 */
@Composable
fun isMediumScreenWidthOrWider(): State<Boolean> {
    val isMediumScreenWidthOrWider = currentWindowAdaptiveInfo().windowSizeClass.isWidthAtLeastBreakpoint(
        widthDpBreakpoint = WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND,
    )

    return rememberUpdatedState(isMediumScreenWidthOrWider)
}
