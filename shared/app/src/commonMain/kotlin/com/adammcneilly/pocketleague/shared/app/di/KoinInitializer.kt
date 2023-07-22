package com.adammcneilly.pocketleague.shared.app.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

private fun appModule(): List<Module> {
    return listOf(
        dateTimeModule,
        remoteModule,
    )
}

/**
 * Initialize all of our Koin DI modules.
 */
fun initKoin() {
    startKoin {
        modules(appModule())
    }
}
