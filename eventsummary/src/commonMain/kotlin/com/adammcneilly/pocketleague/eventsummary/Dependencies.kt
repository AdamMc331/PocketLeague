package com.adammcneilly.pocketleague.eventsummary

import com.adammcneilly.pocketleague.event.api.EventRepository
import com.adammcneilly.pocketleague.event.api.GetUpcomingEventSummariesUseCase
import com.adammcneilly.pocketleague.event.implementation.GetUpcomingEventSummariesUseCaseImpl
import com.adammcneilly.pocketleague.event.implementation.SmashGGEventService

/**
 * A collection of dependencies used within this feature module.
 */
internal object Dependencies {
    val eventRepository: EventRepository
        get() = SmashGGEventService()

    val getUpcomingEventSummariesUseCase: GetUpcomingEventSummariesUseCase
        get() = GetUpcomingEventSummariesUseCaseImpl(
            repository = eventRepository,
        )
}
