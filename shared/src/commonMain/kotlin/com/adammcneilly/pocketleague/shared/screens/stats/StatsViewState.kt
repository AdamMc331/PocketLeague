package com.adammcneilly.pocketleague.shared.screens.stats

import com.adammcneilly.pocketleague.shared.screens.ScreenState

/**
 * The view state for the stats screen.
 */
data class StatsViewState(
    val showLoading: Boolean = true,
) : ScreenState
