package com.adammcneilly.pocketleague.phase.ui

import com.adammcneilly.pocketleague.shared.core.ui.UIText

/**
 * A UI configuration for the phase detail screen.
 */
data class PhaseDetailViewState(
    val showLoading: Boolean = true,
    val phase: PhaseDetailDisplayModel? = null,
    val errorMessage: UIText? = null,
)
