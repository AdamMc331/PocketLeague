package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.data.event.api.RemoteEventService
import com.adammcneilly.pocketleague.data.event.impl.OctaneGGEventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.game.OctaneGGGameService
import com.adammcneilly.pocketleague.data.match.api.RemoteMatchService
import com.adammcneilly.pocketleague.data.match.impl.OctaneGGMatchService
import com.adammcneilly.pocketleague.data.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.data.player.OctaneGGPlayerService
import com.adammcneilly.pocketleague.data.player.RemotePlayerService
import com.adammcneilly.pocketleague.data.remote.BaseKTORClient
import org.koin.dsl.module

val remoteModule = module {
    single<BaseKTORClient> {
        OctaneGGAPIClient
    }

    single<RemoteMatchService> {
        OctaneGGMatchService(
            apiClient = get(),
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
        )
    }

    single<RemotePlayerService> {
        OctaneGGPlayerService(
            apiClient = get(),
        )
    }
}
