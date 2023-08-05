package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Implementation of a [RemoteImage] for the iOS platform.
 */
@Composable
actual fun RemoteImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier,
) {
    // No-Op, images aren't supported on iOS yet.
    // https://code.cash.app/multiplatform-image-loading
    Box(modifier)
}
