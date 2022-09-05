package com.adammcneilly.pocketleague.shared.data

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.match.MatchService
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
        EventService.provideDefault()
    }

    internal val matchService: MatchService by lazy {
        MatchService.provideDefault()
    }

    internal val gameService: GameService by lazy {
        GameService.provideDefault()
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
