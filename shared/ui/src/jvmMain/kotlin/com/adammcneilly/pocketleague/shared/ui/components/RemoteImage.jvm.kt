package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Implementation of a [RemoteImage] for the JVM platform.
 */
@Composable
actual fun RemoteImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier,
) {
    // No-Op, don't have an image loader for JVM yet.
    Box(modifier)
}
