package com.adammcneilly.pocketleague.data.match.impl

import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.EventStage
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

    @Test
    fun getMatchesInDateRange() = runTest {
        val matchListJson = readTestData("match_list.json")
        val expectedEndpoint = "/matches?after=startDateUTC&before=endDateUTC&group=rlcs"

        val client = FakeKTORClient(
            mockResponses = mapOf(
                expectedEndpoint to matchListJson,
            ),
        )

        val service = OctaneGGMatchService(
            apiClient = client,
        )

        val request = MatchListRequest.DateRange(
            startDateUTC = "startDateUTC",
            endDateUTC = "endDateUTC",
        )

        val response = service.fetch(request).getOrThrow()
        assertThat(response.size).isEqualTo(2)
        assertThat(response[0].id.id).isEqualTo("63f5ec055a20c5676ad3a6db")
        assertThat(response[1].id.id).isEqualTo("63f5ec055a20c5676ad3a6dc")
    }

    @Test
    fun getMatchesForEventStage() = runTest {
        val matchListJson = readTestData("match_list.json")
        val expectedEndpoint = "/matches?event=eventId&stage=stageId&group=rlcs"

        val client = FakeKTORClient(
            mockResponses = mapOf(
                expectedEndpoint to matchListJson,
            ),
        )

        val service = OctaneGGMatchService(
            apiClient = client,
        )

        val request = MatchListRequest.EventStage(
            eventId = Event.Id("eventId"),
            stageId = EventStage.Id("stageId"),
        )

        val response = service.fetch(request).getOrThrow()
        assertThat(response.size).isEqualTo(2)
        assertThat(response[0].id.id).isEqualTo("63f5ec055a20c5676ad3a6db")
        assertThat(response[1].id.id).isEqualTo("63f5ec055a20c5676ad3a6dc")
    }
}
