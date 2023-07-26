package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.data.event.OfflineFirstEventRepository
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.adammcneilly.pocketleague.data.match.OfflineFirstMatchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MatchRepository> {
        OfflineFirstMatchRepository(
            localDataSource = get(),
            remoteDataSource = get(),
        )
    }

    single<EventRepository> {
        OfflineFirstEventRepository(
            localEventService = get(),
            remoteEventService = get(),
        )
    }
}
