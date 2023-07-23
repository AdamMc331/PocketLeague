package com.adammcneilly.pocketleague.data.game

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.remote.test.FakeKTORClient
import kotlinx.coroutines.test.runTest
import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.test.Test
import kotlin.test.assertEquals

class OctaneGGGameServiceTest {

    @Test
    fun fetchGamesForMatch() = runTest {
        val mockDataPath = "${getEnv("POCKETLEAGUE_ROOT")!!}/data/game/src/commonTest/resources/game_list.json".toPath()
        println("PATH WAS: $mockDataPath")

        val mockJson = FileSystem.SYSTEM.read(mockDataPath) {
            readUtf8()
        }

        println("OUTPUT WAS: $mockJson")

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
