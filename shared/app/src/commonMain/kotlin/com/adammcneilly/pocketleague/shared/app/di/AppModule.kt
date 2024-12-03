package com.adammcneilly.pocketleague.shared.app.di

import org.koin.dsl.module

val appModule = module {
    includes(
        debugModule,
        octaneGGModule,
        repositoryModule,
        useCaseModule,
        utilModule,
        viewModelModule,
    )
}
