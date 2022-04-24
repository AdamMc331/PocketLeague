package com.adammcneilly.pocketleague.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.adammcneilly.pocketleague.shared.screens.DKMPViewModel

/**
 * The main entry point of any compose based application.
 */
@Composable
fun MainComposable(viewModel: DKMPViewModel) {
    val appState by viewModel.stateFlow.collectAsState()
    val dkmpNav = appState.getNavigation(viewModel)
    dkmpNav.Router()
}
