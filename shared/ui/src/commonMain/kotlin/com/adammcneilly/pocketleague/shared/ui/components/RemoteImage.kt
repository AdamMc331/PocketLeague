package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun RemoteImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    KamelImage(
        resource = asyncPainterResource(imageUrl),
        contentDescription = contentDescription,
        modifier = modifier,
    )
}
