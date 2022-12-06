package com.adammcneilly.pocketleague.shared.di

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.local.PocketLeagueDB
import com.adammcneilly.pocketleague.data.match.MatchService
import com.adammcneilly.pocketleague.data.team.TeamRepository

/**
 * Defines some collection of data layer dependencies used by our application.
 */
interface DataModule {

    val eventService: EventService

    val matchService: MatchService

    val gameService: GameService

    val teamRepository: TeamRepository

    val database: PocketLeagueDB
}
