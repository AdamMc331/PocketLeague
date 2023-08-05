package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Displays an image from a remote [imageUrl].
 */
@Composable
expect fun RemoteImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier,
)
