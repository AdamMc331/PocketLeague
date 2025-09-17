package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.adammcneilly.pocketleague.shared.ui.UiImage
import org.jetbrains.compose.resources.painterResource

/**
 * Wrapper around an [image] that will render the correct component
 * based on the image type.
 */
@Composable
fun ImageWrapper(
    image: UiImage,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    when (image) {
        is UiImage.Local -> {
            LocalImage(
                image = image,
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = modifier,
            )
        }
        is UiImage.Remote -> {
            RemoteImage(
                image = image,
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun RemoteImage(
    image: UiImage.Remote,
    contentDescription: String?,
    contentScale: ContentScale,
    modifier: Modifier,
) {
    AsyncImage(
        model = image.url,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
    )
}

@Composable
private fun LocalImage(
    image: UiImage.Local,
    contentDescription: String?,
    contentScale: ContentScale,
    modifier: Modifier,
) {
    Image(
        painter = painterResource(image.resource),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
    )
}
