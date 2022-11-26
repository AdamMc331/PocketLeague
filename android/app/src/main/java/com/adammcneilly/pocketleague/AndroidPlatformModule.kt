package com.adammcneilly.pocketleague

import android.content.Context
import com.adammcneilly.pocketleague.data.local.DatabaseDriverFactory
import com.adammcneilly.pocketleague.shared.di.PlatformModule

/**
 * An implementation of [PlatformModule] that defines all the dependencies
 * used on the Android platform.
 */
class AndroidPlatformModule(
    appContext: Context,
) : PlatformModule {

    override val databaseDriverFactory: DatabaseDriverFactory by lazy {
        DatabaseDriverFactory(appContext)
    }
}
