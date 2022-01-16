package com.adammcneilly.pocketleague.eventoverview.domain.state

/**
 * A collection of possible actions that can occur on the event overview screen.
 */
sealed class EventOverviewAction {
    /**
     * When this action is triggered we should load an event overview with the given [eventId].
     */
    data class FetchEventOverview(
        val eventId: String,
    ) : EventOverviewAction()

    /**
     * Handle a user click on a specific [phaseId].
     */
    data class SelectPhase(
        val phaseId: String,
    ) : EventOverviewAction()

    /**
     * When we have navigated away to a phase detail, we should clear the selected phase.
     */
    object NavigatedToPhaseDetail : EventOverviewAction()
}
