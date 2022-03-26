package com.adammcneilly.pocketleague.eventsummary

import com.adammcneilly.pocketleague.shared.data.event.EventRepository
import com.adammcneilly.pocketleague.shared.eventsummarylist.domain.GetEventSummariesUseCase
import com.adammcneilly.pocketleague.event.implementation.GetEventSummariesUseCaseImpl
import com.adammcneilly.pocketleague.event.implementation.SmashGGEventService

/**
 * A collection of dependencies used within this feature module.
 */
internal object Dependencies {
    val eventRepository: EventRepository
        get() = SmashGGEventService()

    val getEventSummariesUseCase: GetEventSummariesUseCase
        get() = GetEventSummariesUseCaseImpl(
            repository = eventRepository,
        )
}
