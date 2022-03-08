package com.adammcneilly.pocketleague.dkmp.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.adammcneilly.pocketleague.shared.DKMPViewModel

/**
 * The entry point Composable for our application.
 */
@Composable
fun MainComposable(
    viewModel: DKMPViewModel,
    modifier: Modifier = Modifier,
) {
    val appState by viewModel.stateFlow.collectAsState()
    println("PocketLeague: recomposition Index: " + appState.recompositionIndex.toString())

    val dkmpNav = appState.getNavigation(viewModel)

    dkmpNav.Router(modifier)
}
