package com.adammcneilly.pocketleague.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.screens.stats.StatsViewState

/**
 * The content that renders the [viewState] for the stats screen.
 */
@Composable
fun StatsContent(
    viewState: StatsViewState,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        Text(text = "Stats screen: $viewState")
    }
}
