package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.data.event.OfflineFirstEventRepository
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchFetcher
import com.adammcneilly.pocketleague.data.match.OfflineFirstMatchRepository
import com.adammcneilly.pocketleague.data.player.OfflineFirstPlayerRepository
import com.adammcneilly.pocketleague.data.player.PlayerRepository
import com.adammcneilly.pocketleague.data.team.OctaneGGTeamRepository
import com.adammcneilly.pocketleague.data.team.OfflineFirstTeamRepository
import com.adammcneilly.pocketleague.data.team.SQLDelightTeamRepository
import com.adammcneilly.pocketleague.data.team.TeamRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MatchRepository> {
        OfflineFirstMatchRepository(
            localDataSource = get(),
            remoteDataSource = get(),
            remoteMatchFetcher = OctaneGGMatchFetcher(
                apiClient = get(),
                timeProvider = get(),
            ),
        )
    }

    single<EventRepository> {
        OfflineFirstEventRepository(
            localEventService = get(),
            remoteEventService = get(),
        )
    }

    single<PlayerRepository> {
        OfflineFirstPlayerRepository(
            localDataSource = get(),
            remoteDataSource = get(),
        )
    }

    single<TeamRepository> {
        OfflineFirstTeamRepository(
            localDataSource = SQLDelightTeamRepository(
                database = get(),
            ),
            remoteDataSource = OctaneGGTeamRepository(
                apiClient = get(),
            ),
        )
    }
}
