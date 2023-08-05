package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

/**
 * Implementation of a [RemoteImage] for the Android platform.
 */
@Composable
actual fun RemoteImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier,
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = modifier,
    )
}
