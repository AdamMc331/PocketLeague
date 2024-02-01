package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.feature.debugmenu.DebugPreferences
import com.adammcneilly.pocketleague.feature.debugmenu.SharedDebugPreferences
import org.koin.dsl.module

/**
 * Defines any dependencies related
 */
val debugModule = module {
    single<DebugPreferences> {
        SharedDebugPreferences()
    }
}
