package com.adammcneilly.pocketleague.shared.screens.records

import com.adammcneilly.pocketleague.core.feature.ScreenState

/**
 * The UI state of the records screen.
 */
data class RecordsViewState(
    val showLoading: Boolean = true,
) : ScreenState {

    override val title: String? = null
}
