package com.adammcneilly.pocketleague.feature.debugmenu

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.presenter.Presenter

/**
 * State management for the [DebugMenuScreen].
 */
internal class DebugMenuPresenter : Presenter<DebugMenuScreen.State> {
    @Composable
    override fun present(): DebugMenuScreen.State {
        return DebugMenuScreen.State
    }
}
