package com.adammcneilly.pocketleague.phase.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

/**
 * A screen for phase detail information managed by the supplied [viewModel].
 */
@Destination(
    navArgsDelegate = PhaseDetailNavArgs::class,
)
@Composable
fun PhaseDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PhaseDetailViewModel = hiltViewModel(),
) {
    val viewState = viewModel.viewState.collectAsState()

    PhaseDetailContent(
        viewState = viewState.value,
        modifier = modifier,
    )
}
