package com.adammcneilly.pocketleague.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.feed.FeedViewState

/**
 * Shows content inside the feed screen for the given [viewState].
 */
@Composable
fun FeedContent(
    viewState: FeedViewState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = viewState.toString(),
    )
}
