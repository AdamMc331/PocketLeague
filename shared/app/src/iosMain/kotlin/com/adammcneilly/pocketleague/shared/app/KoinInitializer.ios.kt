package com.adammcneilly.pocketleague.shared.app

import com.adammcneilly.pocketleague.shared.app.di.initKoin
import org.koin.dsl.module

/**
 * A wrapper around [initKoin] that will initialize for the iOS platform.
 */
fun initKoinIos() {
    val appModule = module {
        // Nothing yet
    }

    initKoin(appModule)
}
