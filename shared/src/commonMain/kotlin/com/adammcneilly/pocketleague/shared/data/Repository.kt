package com.adammcneilly.pocketleague.shared.data

import com.adammcneilly.pocketleague.shared.data.remote.octanegg.OctaneGGAPIClient
import com.adammcneilly.pocketleague.shared.data.remote.octanegg.services.OctaneGGEventService
import com.adammcneilly.pocketleague.shared.data.repositories.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This class combines all of the relevant data sources used by the pocket league application.
 *
 * @property[useDefaultDispatcher] Defaults to true, meaning we run async work on Dispatchers.Default.
 * We can supply a value of false to be used for testing.
 */
class Repository(
    private val useDefaultDispatcher: Boolean = true,
) {
    internal val eventRepository: EventRepository by lazy {
        OctaneGGEventService(
            apiClient = OctaneGGAPIClient(),
        )
    }

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
