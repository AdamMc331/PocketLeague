package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.shared.app.data.octanegg.OctaneGGKtorClient
import com.adammcneilly.pocketleague.shared.app.data.remote.BaseKtorClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val OCTANEGG_CLIENT = "octanegg"

val remoteModule = module {
    single<BaseKtorClient>(named(OCTANEGG_CLIENT)) {
        OctaneGGKtorClient
    }
}
