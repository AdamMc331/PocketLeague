package com.adammcneilly.pocketleague.shared.eventoverview.state

import com.adammcneilly.pocketleague.shared.core.ui.UIText
import com.adammcneilly.pocketleague.shared.eventoverview.ui.EventOverviewDisplayModel

/**
 * A collection of possible view states for [EventOverviewScreen].
 */
data class EventOverviewViewState(
    val showLoading: Boolean = true,
    val event: EventOverviewDisplayModel? = null,
    val selectedPhaseId: String? = null,
    val errorMessage: UIText? = null,
)
