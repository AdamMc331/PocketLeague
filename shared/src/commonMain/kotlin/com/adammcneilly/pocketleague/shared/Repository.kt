package com.adammcneilly.pocketleague.shared

import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(val settings: Settings = Settings(), val useDefaultDispatcher: Boolean = true) {

//    internal val webservices by lazy { ApiClient() }
//    internal val localDb by lazy { LocalDb(sqlDriver) }
    internal val localSettings by lazy { PocketLeagueSettings(settings) }
//    internal val runtimeCache get() = CacheObjects

    // we run each repository function on a Dispatchers.Default coroutine
    // we pass useDefaultDispatcher=false just for the TestRepository instance
    suspend fun <T> withRepoContext(block: suspend () -> T): T {
        return if (useDefaultDispatcher) {
            withContext(Dispatchers.Default) {
                block()
            }
        } else {
            block()
        }
    }
}
