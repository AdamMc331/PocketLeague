package com.adammcneilly.pocketleague.eventsummary

import com.adammcneilly.pocketleague.shared.data.event.EventRepository
import com.adammcneilly.pocketleague.shared.data.event.remote.smashgg.SmashGGEventService
import com.adammcneilly.pocketleague.shared.eventsummarylist.domain.GetEventSummariesUseCase
import com.adammcneilly.pocketleague.shared.eventsummarylist.domain.GetEventSummariesUseCaseImpl

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
