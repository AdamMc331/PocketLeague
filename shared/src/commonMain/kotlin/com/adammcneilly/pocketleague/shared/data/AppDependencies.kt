package com.adammcneilly.pocketleague.shared.data

import com.adammcneilly.pocketleague.shared.data.remote.octanegg.services.OctaneGGEventService
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.services.OctaneGGGameService
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.services.OctaneGGMatchService
import com.adammcneilly.pocketleague.shared.data.repositories.EventRepository
import com.adammcneilly.pocketleague.shared.data.repositories.GameRepository
import com.adammcneilly.pocketleague.shared.data.repositories.MatchRepository
import com.adammcneilly.pocketleague.shared.eventlist.GetUpcomingEventsUseCase
import com.adammcneilly.pocketleague.shared.eventlist.GetUpcomingEventsUseCaseImpl
import com.adammcneilly.pocketleague.shared.matchdetail.GetMatchGamesUseCase
import com.adammcneilly.pocketleague.shared.matchdetail.GetMatchGamesUseCaseImpl
import com.adammcneilly.pocketleague.shared.matchlist.GetRecentMatchesUseCase
import com.adammcneilly.pocketleague.shared.matchlist.GetRecentMatchesUseCaseImpl

/**
 * Defines the collection of dependencies used throughout the application. This should
 * expose interfaces so that the callers don't have to concern themselves
 * with the implementation.
 */
class AppDependencies {
    private val eventRepository: EventRepository by lazy {
        OctaneGGEventService()
    }

    private val matchRepository: MatchRepository by lazy {
        OctaneGGMatchService()
    }

    private val gameRepository: GameRepository by lazy {
        OctaneGGGameService()
    }

    internal val getUpcomingEventsUseCase: GetUpcomingEventsUseCase
        get() = GetUpcomingEventsUseCaseImpl(
            repository = eventRepository,
        )

    internal val getRecentMatchesUseCase: GetRecentMatchesUseCase
        get() = GetRecentMatchesUseCaseImpl(
            repository = matchRepository,
        )

    internal val getMatchGamesUseCase: GetMatchGamesUseCase
        get() = GetMatchGamesUseCaseImpl(
            repository = gameRepository,
        )
}
