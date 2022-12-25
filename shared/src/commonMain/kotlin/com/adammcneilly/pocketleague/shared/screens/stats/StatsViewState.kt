package com.adammcneilly.pocketleague.shared.screens.stats

import com.adammcneilly.pocketleague.core.feature.ScreenState

/**
 * The view state for the stats screen.
 */
data class StatsViewState(
    val showLoading: Boolean = true,
) : com.adammcneilly.pocketleague.core.feature.ScreenState {

    override val title: String? = null
}
