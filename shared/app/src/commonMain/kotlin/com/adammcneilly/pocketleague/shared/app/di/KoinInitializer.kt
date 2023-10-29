package com.adammcneilly.pocketleague.shared.app.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

private fun allModules(): List<Module> {
    return listOf(
        dateTimeModule,
        localModule,
        platformModule,
        remoteModule,
        repositoryModule,
    )
}

/**
 * Initialize all of our Koin DI modules.
 */
fun initKoin(appModule: Module) {
    startKoin {
        modules(appModule)
        modules(allModules())
    }
}
