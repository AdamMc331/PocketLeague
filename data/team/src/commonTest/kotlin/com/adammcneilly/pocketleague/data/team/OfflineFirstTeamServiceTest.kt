package com.adammcneilly.pocketleague.data.team

import com.adammcneilly.pocketleague.core.models.DataState
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGTeamDetail
import com.adammcneilly.pocketleague.data.octanegg.models.OctaneGGTeamListResponse
import com.adammcneilly.pocketleague.data.octanegg.models.toTeam
import com.adammcneilly.pocketleague.data.remote.test.FakeKTORClient
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class OfflineFirstTeamServiceTest {
    @Test
    fun fetchTeams() = runTest {
        val mockResponses = mapOf(
            OfflineFirstTeamService.ACTIVE_TEAMS_ENDPOINT to MockTeamListResponse.json
        )

        val apiClient = FakeKTORClient(mockResponses)

        val apiResponse = apiClient.getResponse<OctaneGGTeamListResponse>(
            endpoint = OfflineFirstTeamService.ACTIVE_TEAMS_ENDPOINT,
        ).map { octaneGGTeamListResponse ->
            octaneGGTeamListResponse.teams
                ?.map(OctaneGGTeamDetail::toTeam)
                .orEmpty()
        }

        println("RESULT: $apiResponse")

        val team = (apiResponse as DataState.Success).data.first()
        assertEquals("1s", team.name)
    }
}
