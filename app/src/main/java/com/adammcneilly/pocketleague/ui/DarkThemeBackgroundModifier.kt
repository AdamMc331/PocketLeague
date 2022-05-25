package com.adammcneilly.pocketleague.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.darkThemeBackgroundModifier(
    color: Color,
    shape: Shape,
): Modifier {
    return if (isSystemInDarkTheme()) {
        this.background(color, shape)
    } else {
        this
    }
}
