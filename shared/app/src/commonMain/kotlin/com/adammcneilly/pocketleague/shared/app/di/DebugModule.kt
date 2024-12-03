package com.adammcneilly.pocketleague.shared.app.di

import org.koin.core.qualifier.named
import org.koin.dsl.module

const val USE_DEBUG_DATA = "use_debug_data"

val debugModule = module {
    single<Boolean>(named(USE_DEBUG_DATA)) {
        true
    }
}
