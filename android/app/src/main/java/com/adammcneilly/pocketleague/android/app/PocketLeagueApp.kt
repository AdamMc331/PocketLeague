package com.adammcneilly.pocketleague.android.app

import android.app.Application
import android.content.Context
import com.adammcneilly.pocketleague.shared.app.di.initKoin
import org.koin.dsl.module

/**
 * The [Application] instance for Pocket League.
 */
class PocketLeagueApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            appModule = module {
                single<Context> { this@PocketLeagueApp }
            },
        )
    }
}
