package com.adammcneilly.pocketleague.shared.eventsummarylist

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.models.EventSummary
import com.adammcneilly.pocketleague.core.ui.UIImage
import com.adammcneilly.pocketleague.shared.Events
import com.adammcneilly.pocketleague.shared.datalayer.objects.EventListRequestBody
import com.adammcneilly.pocketleague.shared.datalayer.sources.graphql.requests.fetchEventSummaries
import kotlinx.coroutines.flow.collectLatest

/**
 * Requests a flow of events from the supplied [requestBody] and updates our view state whenever
 * an emission is made.
 */
fun Events.getEvents(requestBody: EventListRequestBody) = screenCoroutine {
    val events = dataRepository.smashGGApi.fetchEventSummaries(
        leagueSlug = "rlcs-2021-22-1",
        requestBody = requestBody,
    )

    events.collectLatest { result ->
        stateManager.updateScreen(EventSummaryListState::class) { currentState ->
            when (result) {
                is Result.Success -> {
                    currentState.copy(
                        showLoading = false,
                        events = result.data.map(EventSummary::toSummaryDisplayModel),
                    )
                }
                is Result.Error -> {
                    currentState.copy(
                        showLoading = false,
                        errorMessage = result.error.message,
                    )
                }
            }
        }
    }
}

/**
 * Given an [eventId], mark that event as selected inside the [EventSummaryListState.events] list.
 */
fun Events.selectEvent(eventId: String) {
    stateManager.updateScreen(EventSummaryListState::class) { currentState ->
        val updatedEvents = currentState.events.map { oldEvent ->
            oldEvent.copy(
                isSelected = (oldEvent.eventId == eventId)
            )
        }

        currentState.copy(
            events = updatedEvents,
        )
    }
}

/**
 * Converts an [EventSummary] domain object to a user friendly [EventSummaryDisplayModel].
 */
private fun EventSummary.toSummaryDisplayModel(): EventSummaryDisplayModel {
    return EventSummaryDisplayModel(
        eventId = this.id,
        startDate = DateTimeFormatter().formatLocalDateTime(
            this.startDate,
            EventSummaryDisplayModel.START_DATE_FORMAT,
        ).orEmpty(),
        tournamentName = this.tournamentName,
        eventName = this.eventName,
        subtitle = this.buildSubtitle(),
        image = UIImage.Remote(
            imageUrl = this.tournamentImageUrl,
        ),
    )
}

/**
 * Generates a user friendly subtitle for an [EventSummary] intended to be passed to an [EventSummaryDisplayModel].
 */
private fun EventSummary.buildSubtitle(): String? {
    return this.numEntrants?.let { numEntrants ->
        "$numEntrants Teams"
    }
}
