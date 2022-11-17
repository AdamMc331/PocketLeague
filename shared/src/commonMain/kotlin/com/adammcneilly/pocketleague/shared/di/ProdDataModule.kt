package com.adammcneilly.pocketleague.shared.di

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.match.MatchService
import com.adammcneilly.pocketleague.data.team.OctaneGGTeamService
import com.adammcneilly.pocketleague.data.team.TeamService

/**
 * A concrete implementation of [DataModule] that defines all of the dependencies
 * used in a production scenario.
 */
class ProdDataModule : DataModule {

    override val eventService: EventService by lazy {
        EventService.provideDefault()
    }

    override val matchService: MatchService by lazy {
        MatchService.provideDefault()
    }

    override val gameService: GameService by lazy {
        GameService.provideDefault()
    }
    override val teamService: TeamService by lazy {
        OctaneGGTeamService()
    }
}
