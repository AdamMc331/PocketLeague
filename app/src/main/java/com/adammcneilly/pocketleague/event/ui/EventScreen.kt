package com.adammcneilly.pocketleague.event.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EventScreen(
    modifier: Modifier = Modifier,
    viewModel: EventViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()

    EventContent(
        viewState = viewState.value,
        modifier = modifier,
    )
}
