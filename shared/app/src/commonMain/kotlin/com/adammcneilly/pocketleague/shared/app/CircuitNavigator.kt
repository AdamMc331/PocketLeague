package com.adammcneilly.pocketleague.shared.app

import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.screen.PopResult

/**
 * Provide an implementation of [Navigator] with a given [backStack]
 */
@Composable
expect fun provideCircuitNavigator(
    backStack: SaveableBackStack,
    onRootPop: (PopResult?) -> Unit,
): Navigator
