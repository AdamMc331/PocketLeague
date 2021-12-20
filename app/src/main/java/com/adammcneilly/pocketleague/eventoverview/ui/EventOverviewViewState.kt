package com.adammcneilly.pocketleague.eventoverview.ui

import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.phase.domain.models.Phase

/**
 * A collection of possible view states for [EventOverviewScreen].
 */
sealed class EventOverviewViewState {
    /**
     * The initial state of the screen when we are loading an event view state.
     */
    object Loading : EventOverviewViewState()

    /**
     * The success state of the screen after an [event] has been loaded.
     */
    data class Success(
        val event: EventOverviewDisplayModel,
        val selectedPhase: Phase? = null,
    ) : EventOverviewViewState()

    /**
     * The failure state when a request to load an event is unsuccessful.
     *
     * @property[errorMessage] A user friendly representation of the problem.
     */
    data class Error(
        val errorMessage: UIText,
    ) : EventOverviewViewState()
}
