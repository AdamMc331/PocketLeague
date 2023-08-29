package com.adammcneilly.pocketleague.data.match

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.test.readTestData
import com.adammcneilly.pocketleague.data.remote.test.FakeKTORClient
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class OctaneGGMatchServiceTest {

    @Test
    fun getMatchDetail() = runTest {
        val matchJson = readTestData("match_detail.json")

        val client = FakeKTORClient(
            mockResponses = mapOf(
                "/matches/123" to matchJson,
            ),
        )

        val service = OctaneGGMatchService(
            apiClient = client,
            timeProvider = DebugClock(),
        )

        val response = service.getMatchDetail(Match.Id("123")).getOrThrow()

        assertThat(response.blueTeam.team.name).isEqualTo("Karmine Corp")
        assertThat(response.orangeTeam.team.name).isEqualTo("James Cheese")
    }
}
