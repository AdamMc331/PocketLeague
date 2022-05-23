package com.adammcneilly.pocketleague.shared.data

import com.adammcneilly.pocketleague.core.data.remote.octanegg.services.OctaneGGEventService
import com.adammcneilly.pocketleague.core.data.remote.octanegg.services.OctaneGGGameService
import com.adammcneilly.pocketleague.core.data.remote.octanegg.services.OctaneGGMatchService
import com.adammcneilly.pocketleague.core.data.repositories.EventRepository
import com.adammcneilly.pocketleague.core.data.repositories.GameRepository
import com.adammcneilly.pocketleague.core.data.repositories.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * The main entry point to various data layers inside the application.
 *
 * @property[useDefaultDispatcher] Most often true, meaning repo calls should be run on the
 * Dispatchers.Default dispatcher. We can toggle this false for tests, though.
 */
class Repository(
    private val useDefaultDispatcher: Boolean = true,
) {
    internal val eventRepository: EventRepository by lazy {
        OctaneGGEventService()
    }

    internal val matchRepository: MatchRepository by lazy {
        OctaneGGMatchService()
    }

    internal val gameRepository: GameRepository by lazy {
        OctaneGGGameService()
    }

    /**
     * Runs the supplied [block] inside the scope for this repo, dependent
     * oon the [useDefaultDispatcher] flag.
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
