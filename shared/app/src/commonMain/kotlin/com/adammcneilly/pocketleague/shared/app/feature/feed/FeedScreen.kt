package com.adammcneilly.pocketleague.shared.app.feature.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

/**
 * Top level container for the Feed screen inside the application. This is a stateful
 * wrapper around [FeedContent].
 */
@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsState()

    FeedContent(
        recentMatches = state.value.recentMatches,
        ongoingEvents = state.value.ongoingEvents,
        upcomingEvents = state.value.upcomingEvents,
        onMatchClicked = { /*TODO*/ },
        onEventClicked = { /*TODO*/ },
        modifier = modifier,
    )
}
