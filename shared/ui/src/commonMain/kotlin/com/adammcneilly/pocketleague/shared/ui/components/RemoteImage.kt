package com.adammcneilly.pocketleague.shared.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun RemoteImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier,
)
