package com.adammcneilly.pocketleague.shared.di

import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.data.event.OctaneGGEventService
import com.adammcneilly.pocketleague.data.event.OfflineFirstEventRepository
import com.adammcneilly.pocketleague.data.event.SQLDelightEventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.game.OctaneGGGameService
import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
import com.adammcneilly.pocketleague.data.match.OfflineFirstMatchRepository
import com.adammcneilly.pocketleague.data.match.SQLDelightMatchService
import com.adammcneilly.pocketleague.data.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.data.team.OctaneGGTeamRepository
import com.adammcneilly.pocketleague.data.team.OfflineFirstTeamRepository
import com.adammcneilly.pocketleague.data.team.SQLDelightTeamRepository
import com.adammcneilly.pocketleague.data.team.TeamRepository

/**
 * A concrete implementation of [DataModule] that defines all of the dependencies
 * used in a production scenario.
 */
class ProdDataModule(
    private val databaseDriverFactory: DatabaseDriverFactory,
) : DataModule {

    override val eventRepository: EventRepository by lazy {
        OfflineFirstEventRepository(
            localEventService = SQLDelightEventService(this.database),
            remoteEventService = OctaneGGEventService(),
        )
    }

    override val matchRepository: MatchRepository by lazy {
        OfflineFirstMatchRepository(
            localDataSource = SQLDelightMatchService(this.database),
            remoteDataSource = OctaneGGMatchService(),
        )
    }

    override val gameService: GameService by lazy {
        OctaneGGGameService()
    }

    override val teamRepository: TeamRepository by lazy {
        OfflineFirstTeamRepository(
            localDataSource = SQLDelightTeamRepository(this.database),
            remoteDataSource = OctaneGGTeamRepository(OctaneGGAPIClient),
        )
    }

    override val database: PocketLeagueDB
        get() = PocketLeagueDB(databaseDriverFactory.createDriver())
}
