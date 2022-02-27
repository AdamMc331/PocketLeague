package com.adammcneilly.pocketleague.eventsummary

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * A hardcoded version of [eventSummaryListStateMutator] that will be used on iOS. Any changes
 * to the state will be passed through the supplied [onChange] lambda.
 *
 * This needs to be cleaned up in the future so we can actually close this job when we want to.
 */
fun iOSEventSummaryListStateMutator(
    onChange: (EventSummaryListViewState) -> Unit,
) = eventSummaryListStateMutator(
    getUpcomingEventsUseCase = Dependencies.getUpcomingEventSummariesUseCase,
    scope = MainScope(),
).apply {
    this.state
        .onEach { newState ->
            onChange.invoke(newState)
        }
        .launchIn(MainScope())
}
