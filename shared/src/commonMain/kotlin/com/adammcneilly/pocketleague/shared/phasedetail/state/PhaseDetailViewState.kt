package com.adammcneilly.pocketleague.shared.phasedetail.state

import com.adammcneilly.pocketleague.shared.core.ui.UIText
import com.adammcneilly.pocketleague.shared.phasedetail.ui.PhaseDetailDisplayModel

/**
 * A UI configuration for the phase detail screen.
 */
data class PhaseDetailViewState(
    val showLoading: Boolean = true,
    val phase: PhaseDetailDisplayModel? = null,
    val errorMessage: UIText? = null,
)
