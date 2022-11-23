package com.adammcneilly.pocketleague.shared.test

import com.adammcneilly.pocketleague.data.event.EventService
import com.adammcneilly.pocketleague.data.event.test.FakeEventService
import com.adammcneilly.pocketleague.data.game.GameService
import com.adammcneilly.pocketleague.data.match.MatchService
import com.adammcneilly.pocketleague.data.match.test.FakeMatchService
import com.adammcneilly.pocketleague.data.team.OctaneGGTeamService
import com.adammcneilly.pocketleague.data.team.TeamService
import com.adammcneilly.pocketleague.shared.di.DataModule

/**
 * A concrete implementation of [DataModule] that provides dependencies
 * to be used in a test situation.
 */
class TestDataModule : DataModule {

    override val eventService: EventService = FakeEventService()

    override val matchService: MatchService = FakeMatchService()

    override val gameService: GameService = GameService.provideDefault()

    override val teamService: TeamService = OctaneGGTeamService()
}
