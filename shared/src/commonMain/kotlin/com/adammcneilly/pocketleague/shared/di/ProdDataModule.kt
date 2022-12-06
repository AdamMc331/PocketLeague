package com.adammcneilly.pocketleague.shared.di

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.event.OfflineFirstEventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.game.OctaneGGGameService
import com.adammcneilly.pocketleague.data.local.DatabaseDriverFactory
import com.adammcneilly.pocketleague.data.local.PocketLeagueDB
import com.adammcneilly.pocketleague.data.match.MatchService
import com.adammcneilly.pocketleague.data.match.OfflineFirstMatchService
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
    databaseDriverFactory: DatabaseDriverFactory,
) : DataModule {

    override val eventService: EventService by lazy {
        OfflineFirstEventService(
            database = this.database,
            apiClient = OctaneGGAPIClient,
        )
    }

    override val matchService: MatchService by lazy {
        OfflineFirstMatchService(
            database = this.database,
            apiClient = OctaneGGAPIClient,
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

    override val database: PocketLeagueDB by lazy {
        PocketLeagueDB(databaseDriverFactory.createDriver())
    }
}
