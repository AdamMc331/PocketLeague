package com.adammcneilly.pocketleague.shared.app

import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.runtime.Navigator

/**
 * Provide an implementation of [Navigator] with a given [backstack]
 */
@Composable
expect fun provideCircuitNavigator(
    backStack: SaveableBackStack,
    onRootPop: () -> Unit,
): Navigator
