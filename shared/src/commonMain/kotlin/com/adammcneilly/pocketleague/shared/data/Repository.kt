package com.adammcneilly.pocketleague.shared.data

import com.adammcneilly.pocketleague.shared.data.remote.octanegg.services.OctaneGGEventService
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.services.OctaneGGMatchService
import com.adammcneilly.pocketleague.shared.data.repositories.EventRepository
import com.adammcneilly.pocketleague.shared.data.repositories.MatchRepository
import com.adammcneilly.pocketleague.shared.eventlist.GetUpcomingEventsUseCase
import com.adammcneilly.pocketleague.shared.eventlist.GetUpcomingEventsUseCaseImpl
import com.adammcneilly.pocketleague.shared.matchlist.GetRecentMatchesUseCase
import com.adammcneilly.pocketleague.shared.matchlist.GetRecentMatchesUseCaseImpl

class AppDependencies {
    private val eventRepository: EventRepository by lazy {
        OctaneGGEventService()
    }

    private val matchRepository: MatchRepository by lazy {
        OctaneGGMatchService()
    }

    internal val getUpcomingEventsUseCase: GetUpcomingEventsUseCase
        get() = GetUpcomingEventsUseCaseImpl(
            repository = eventRepository,
        )

    internal val getRecentMatchesUseCase: GetRecentMatchesUseCase
        get() = GetRecentMatchesUseCaseImpl(
            repository = matchRepository,
        )
}
