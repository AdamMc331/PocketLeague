package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.data.event.api.EventRepository
import com.adammcneilly.pocketleague.data.event.impl.StoreEventRepository
import com.adammcneilly.pocketleague.data.match.api.MatchRepository
import com.adammcneilly.pocketleague.data.match.impl.StoreMatchRepository
import com.adammcneilly.pocketleague.data.player.OfflineFirstPlayerRepository
import com.adammcneilly.pocketleague.data.player.PlayerRepository
import com.adammcneilly.pocketleague.data.team.OctaneGGTeamRepository
import com.adammcneilly.pocketleague.data.team.OfflineFirstTeamRepository
import com.adammcneilly.pocketleague.data.team.SQLDelightTeamRepository
import com.adammcneilly.pocketleague.data.team.TeamRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MatchRepository> {
        StoreMatchRepository(
            remoteMatchService = get(),
            localMatchService = get(),
        )
    }

    single<EventRepository> {
        StoreEventRepository(
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
