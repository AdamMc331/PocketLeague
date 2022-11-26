package com.adammcneilly.pocketleague.shared.di

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.event.OctaneGGEventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.game.OctaneGGGameService
import com.adammcneilly.pocketleague.data.local.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.PLSqlDelightDatabase
import com.adammcneilly.pocketleague.data.match.MatchService
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
import com.adammcneilly.pocketleague.data.team.OctaneGGTeamService
import com.adammcneilly.pocketleague.data.team.TeamService

/**
 * A concrete implementation of [DataModule] that defines all of the dependencies
 * used in a production scenario.
 */
class ProdDataModule(
    databaseDriverFactory: DatabaseDriverFactory,
) : DataModule {

    override val eventService: EventService by lazy {
        OctaneGGEventService()
    }

    override val matchService: MatchService by lazy {
        OctaneGGMatchService()
    }

    override val gameService: GameService by lazy {
        OctaneGGGameService()
    }

    override val teamService: TeamService by lazy {
        OctaneGGTeamService()
    }

    override val database: PLSqlDelightDatabase by lazy {
        PLSqlDelightDatabase(databaseDriverFactory)
    }
}
