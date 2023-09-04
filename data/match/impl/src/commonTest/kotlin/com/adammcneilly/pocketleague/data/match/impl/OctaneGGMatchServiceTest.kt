package com.adammcneilly.pocketleague.data.match.impl

import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.test.readTestData
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
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
        )

        val request = MatchListRequest.Id(Match.Id("123"))
        val response = service.fetch(request).getOrThrow().first()

        assertThat(response.blueTeam.team.name).isEqualTo("Karmine Corp")
        assertThat(response.orangeTeam.team.name).isEqualTo("James Cheese")
    }
}
