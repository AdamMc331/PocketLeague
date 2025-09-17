package com.adammcneilly.pocketleague.shared.ui

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

val LocalWindowHeightSizeClassOverride = compositionLocalOf<WindowHeightSizeClass?> { null }
val LocalWindowWidthSizeClassOverride = compositionLocalOf<WindowWidthSizeClass?> { null }

@Composable
expect fun currentWindowSizeClass(): WindowSizeClass

@Composable
fun currentWindowHeightSizeClass(): WindowHeightSizeClass {
    return LocalWindowHeightSizeClassOverride.current ?: currentWindowSizeClass().heightSizeClass
}

@Composable
fun currentWindowWidthSizeClass(): WindowWidthSizeClass {
    return LocalWindowWidthSizeClassOverride.current ?: currentWindowSizeClass().widthSizeClass
}
