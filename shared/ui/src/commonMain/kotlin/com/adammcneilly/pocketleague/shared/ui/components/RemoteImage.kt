package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import coil3.toUri

/**
 * Displays an image from a remote [imageUrl].
 */
@Composable
fun RemoteImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalPlatformContext.current)
            .data(imageUrl.toUri())
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
    )
}
