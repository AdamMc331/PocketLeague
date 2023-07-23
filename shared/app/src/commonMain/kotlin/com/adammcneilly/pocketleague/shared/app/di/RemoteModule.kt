package com.adammcneilly.pocketleague.shared.app.di

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
}
