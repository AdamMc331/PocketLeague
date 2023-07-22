package com.adammcneilly.pocketleague.android.app

import android.app.Application
import com.adammcneilly.pocketleague.shared.app.di.initKoin

/**
 * The [Application] instance for Pocket League.
 */
class PocketLeagueApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}
