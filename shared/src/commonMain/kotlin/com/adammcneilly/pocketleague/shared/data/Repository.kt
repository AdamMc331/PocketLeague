package com.adammcneilly.pocketleague.shared.data

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.event.OctaneGGEventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.game.OctaneGGGameService
import com.adammcneilly.pocketleague.data.match.MatchService
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
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

    internal val eventService: EventService by lazy {
        OctaneGGEventService()
    }

    internal val matchService: MatchService by lazy {
        OctaneGGMatchService()
    }

    internal val gameService: GameService by lazy {
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
