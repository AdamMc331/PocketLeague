package com.adammcneilly.pocketleague.eventsummary.domain.state

import com.adammcneilly.pocketleague.eventsummary.domain.models.EventSummary

/**
 * A collection of possible domain actions that can occur within the event summary list feature.
 */
sealed class EventSummaryListAction {
    /**
     * This action will trigger the loading of event summaries.
     */
    object FetchUpcomingEvents : EventSummaryListAction()

    /**
     * Action fired when the user clicks on a specific [event].
     */
    data class SelectedEvent(
        val event: EventSummary,
    ) : EventSummaryListAction()

    /**
     * Once we know the user has navigated to an event overview, we can clear the selected event
     * to avoid navigating again.
     */
    object NavigatedToEventOverview : EventSummaryListAction()
}
