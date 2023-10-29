package com.adammcneilly.pocketleague.shared.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Wrapper component around a [Spacer] that is used to create one
 * for a fixed [width].
 */
@Composable
fun HorizontalSpacer(width: Dp) {
    Spacer(
        modifier =
            Modifier
                .width(width),
    )
}
