package com.adammcneilly.pocketleague.shared.di

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.match.MatchService
import com.adammcneilly.pocketleague.data.team.TeamService

/**
 * Defines some collection of data layer dependencies used by our application.
 */
interface DataModule {

    val eventService: EventService

    val matchService: MatchService

    val gameService: GameService

    val teamService: TeamService
}
