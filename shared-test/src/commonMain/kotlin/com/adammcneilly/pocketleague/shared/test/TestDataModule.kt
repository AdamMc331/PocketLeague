package com.adammcneilly.pocketleague.shared.test

import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.data.event.test.FakeEventRepository
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.game.OctaneGGGameService
import com.adammcneilly.pocketleague.data.local.sqldelight.PocketLeagueDB
import com.adammcneilly.pocketleague.data.match.MatchService
import com.adammcneilly.pocketleague.data.match.test.FakeMatchService
import com.adammcneilly.pocketleague.data.team.TeamRepository
import com.adammcneilly.pocketleague.shared.di.DataModule

/**
 * A concrete implementation of [DataModule] that provides dependencies
 * to be used in a test situation.
 */
class TestDataModule : DataModule {

    override val eventRepository: EventRepository = FakeEventRepository()

    override val matchService: MatchService = FakeMatchService()

    override val gameService: GameService = OctaneGGGameService()

    override val teamRepository: TeamRepository
        get() = TODO("Not yet implemented")

    override val database: PocketLeagueDB
        get() = TODO("Not yet implemented")
}
