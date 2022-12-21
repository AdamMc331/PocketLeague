package com.adammcneilly.pocketleague.shared.di

import com.adammcneilly.pocketleague.data.local.sqldelight.DatabaseDriverFactory

/**
 * A dependency container for dependencies specific to a platform, that may require
 * Android context for example & can't be created in shared code.
 */
interface PlatformModule {
    val databaseDriverFactory: DatabaseDriverFactory
}
