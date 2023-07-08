package com.adammcneilly.pocketleague.shared.app

import androidx.compose.runtime.Composable
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.Navigator

/**
 * Provide an implementation of [Navigator] for the iOS platform.
 */
@Composable
actual fun provideCircuitNavigator(
    backStack: SaveableBackStack,
    onRootPop: () -> Unit,
): Navigator {
    return rememberCircuitNavigator(
        backstack = backStack,
        onRootPop = onRootPop,
    )
}
