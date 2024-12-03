package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.shared.app.data.event.EventRepository
import com.adammcneilly.pocketleague.shared.app.data.match.MatchRepository
import com.adammcneilly.pocketleague.shared.app.data.octanegg.event.OctaneGGEventRepository
import com.adammcneilly.pocketleague.shared.app.data.octanegg.match.OctaneGGMatchRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<EventRepository> {
        OctaneGGEventRepository(
            apiClient = get(named(OCTANE_GG_CLIENT)),
        )
    }

    single<MatchRepository> {
        OctaneGGMatchRepository(
            apiClient = get(named(OCTANE_GG_CLIENT)),
        )
    }
}
