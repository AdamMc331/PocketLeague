package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.data.event.OctaneGGEventService
import com.adammcneilly.pocketleague.data.event.RemoteEventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.game.OctaneGGGameService
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
import com.adammcneilly.pocketleague.data.match.RemoteMatchService
import com.adammcneilly.pocketleague.data.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import org.koin.dsl.module

val remoteModule = module {
    single<BaseKTORClient> {
        OctaneGGAPIClient
    }

    single<RemoteMatchService> {
        OctaneGGMatchService(
            apiClient = get(),
            clock = get(),
        )
    }

    single<GameService> {
        OctaneGGGameService(
            apiClient = get(),
        )
    }

    single<RemoteEventService> {
        OctaneGGEventService(
            apiClient = get(),
            clock = get(),
        )
    }
}
