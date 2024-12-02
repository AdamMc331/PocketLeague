package com.adammcneilly.pocketleague.shared.app.di

import org.koin.dsl.module

val appModule = module {
    includes(
        utilModule,
        remoteModule,
        repositoryModule,
        useCaseModule,
        viewModelModule,
    )
}
