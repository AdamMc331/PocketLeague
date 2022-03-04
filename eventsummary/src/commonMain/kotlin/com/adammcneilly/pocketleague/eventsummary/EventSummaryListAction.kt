package com.adammcneilly.pocketleague.eventsummary

/**
 * A collection of possible domain actions that can occur within the event summary list feature.
 */
sealed class EventSummaryListAction {
    /**
     * This action will trigger the loading of event summaries.
     */
    data class FetchUpcomingEvents(
        val leagueSlug: String,
    ) : EventSummaryListAction()

    /**
     * Action fired when the user clicks on a specific [eventId].
     */
    data class SelectedEvent(
        val eventId: String,
    ) : EventSummaryListAction()

    /**
     * Action fired when the user changes their sort selection for an event summary list.
     */
    data class SelectedSort(
        val sort: EventSummaryListSort,
    ) : EventSummaryListAction()

    /**
     * Once we know the user has navigated to an event overview, we can clear the selected event
     * to avoid navigating again.
     */
    object NavigatedToEventOverview : EventSummaryListAction()
}
