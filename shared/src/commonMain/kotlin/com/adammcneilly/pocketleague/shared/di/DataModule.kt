package com.adammcneilly.pocketleague.shared.di

import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.adammcneilly.pocketleague.data.team.TeamRepository

/**
 * Defines some collection of data layer dependencies used by our application.
 */
interface DataModule {

    val eventRepository: EventRepository

    val matchRepository: MatchRepository

    val gameService: GameService

    val teamRepository: TeamRepository

    val database: PocketLeagueDB
}
