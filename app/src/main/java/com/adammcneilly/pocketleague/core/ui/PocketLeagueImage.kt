package com.adammcneilly.pocketleague.core.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

/**
 * A custom composable for an [Image] that will set the painter based on the [image] supplied.
 */
@Composable
fun PocketLeagueImage(
    image: UIImage,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    when (image) {
        is UIImage.Resource -> {
            Image(
                painterResource(id = image.drawableRes),
                contentDescription = contentDescription,
                modifier = modifier,
            )
        }
        is UIImage.Remote -> {
            // Coming soon
        }
    }
}
