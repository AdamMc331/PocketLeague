package com.adammcneilly.pocketleague.data.game

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.test.readTestData
import com.adammcneilly.pocketleague.data.remote.test.FakeKTORClient
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class OctaneGGGameServiceTest {

    @Test
    fun fetchGamesForMatch() = runTest {
        val mockJson = readTestData("game_list.json")

        val client = FakeKTORClient(
            mockResponses = mapOf(
                "/matches/123/games" to mockJson,
            ),
        )

        val output = OctaneGGGameService(
            client,
        )
            .fetchGamesForMatch(MatchGamesRequest(Match.Id("123")))
            .getOrThrow()

        assertEquals(3, output.size)
    }
}
