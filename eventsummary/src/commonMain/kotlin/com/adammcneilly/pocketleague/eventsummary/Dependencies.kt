package com.adammcneilly.pocketleague.eventsummary

import com.adammcneilly.pocketleague.event.api.EventRepository
import com.adammcneilly.pocketleague.event.api.GetEventSummariesUseCase
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
