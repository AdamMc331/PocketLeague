package com.adammcneilly.pocketleague.shared

import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * The data layer for our application. It should manage all sources from web services, local DB,
 * settings, and even runtime cache.
 */
class Repository(
    private val settings: Settings = Settings(),
    private val useDefaultDispatcher: Boolean = true,
) {

//    internal val webservices by lazy { ApiClient() }
//    internal val localDb by lazy { LocalDb(sqlDriver) }
    internal val localSettings by lazy { PocketLeagueSettings(settings) }
//    internal val runtimeCache get() = CacheObjects

    /**
     * Each repository function should be run on [Dispatchers.Default] coroutine scope. However,
     * for a test repository instance we can pass [useDefaultDispatcher] to false.
     */
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
