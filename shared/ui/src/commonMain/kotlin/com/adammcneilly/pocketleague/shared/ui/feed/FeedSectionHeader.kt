package com.adammcneilly.pocketleague.shared.ui.feed

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Header component for a section of items within the [FeedContent].
 */
@Composable
fun FeedSectionHeader(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier,
    )
}
