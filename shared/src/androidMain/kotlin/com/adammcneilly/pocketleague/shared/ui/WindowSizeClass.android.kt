package com.adammcneilly.pocketleague.shared.ui

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
actual fun currentWindowSizeClass(): WindowSizeClass {
    val windowSizeClass = LocalActivity.current?.let {
        calculateWindowSizeClass(it)
    }

    return checkNotNull(windowSizeClass) {
        "Cannot create WindowSizeClass outside of an Activity."
    }
}
