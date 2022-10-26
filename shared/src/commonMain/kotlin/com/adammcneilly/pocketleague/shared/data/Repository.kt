package com.adammcneilly.pocketleague.shared.data

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.match.MatchService
import com.adammcneilly.pocketleague.data.team.TeamService
import com.adammcneilly.pocketleague.shared.di.DataModule
import com.adammcneilly.pocketleague.shared.di.ProdDataModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * The main entry point to various data layers inside the application.
 *
 * @property[dataModule] A collection of data related dependencies for this repository.
 * @property[useDefaultDispatcher] Most often true, meaning repo calls should be run on the
 * Dispatchers.Default dispatcher. We can toggle this false for tests, though.
 */
class Repository(
    private val dataModule: DataModule = ProdDataModule(),
    private val useDefaultDispatcher: Boolean = true,
) {

    internal val eventService: EventService by lazy {
        dataModule.eventService
    }

    internal val matchService: MatchService by lazy {
        dataModule.matchService
    }

    internal val gameService: GameService by lazy {
        dataModule.gameService
    }

    internal val teamService: TeamService by lazy {
        dataModule.teamService
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
