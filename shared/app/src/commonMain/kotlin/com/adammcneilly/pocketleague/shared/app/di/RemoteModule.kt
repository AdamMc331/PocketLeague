package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.data.event.OctaneGGEventService
import com.adammcneilly.pocketleague.data.event.RemoteEventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.game.OctaneGGGameService
import com.adammcneilly.pocketleague.data.match.MatchFetcher
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchFetcher
import com.adammcneilly.pocketleague.data.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.data.player.OctaneGGPlayerService
import com.adammcneilly.pocketleague.data.player.RemotePlayerService
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import org.koin.dsl.module

val remoteModule = module {
    single<BaseKTORClient> {
        OctaneGGAPIClient
    }

    single<MatchFetcher> {
        OctaneGGMatchFetcher(
            apiClient = get(),
            timeProvider = get(),
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
            timeProvider = get(),
        )
    }

    single<RemotePlayerService> {
        OctaneGGPlayerService(
            apiClient = get(),
        )
    }
}
