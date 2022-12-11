package com.adammcneilly.pocketleague.data.team

import app.cash.turbine.test
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.team
import com.adammcneilly.pocketleague.data.remote.test.FakeKTORClient
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull

class OctaneGGTeamRepositoryTest {

    @Test
    fun throwIfRequestingFavoriteTeams() {
        val repository = OctaneGGTeamRepository(FakeKTORClient())

        assertFailsWith(UnsupportedOperationException::class) {
            repository.getFavoriteTeams()
        }
    }

    @Test
    fun throwIfInsertingATeam() = runTest {
        val repository = OctaneGGTeamRepository(FakeKTORClient())

        assertFailsWith(UnsupportedOperationException::class) {
            repository.insertTeams(listOf(TestModel.team))
        }
    }

    @Test
    fun getActiveRLCSTeams() = runTest {
        val mockResponses = mapOf(
            OctaneGGTeamRepository.ACTIVE_TEAMS_ENDPOINT to MockTeamListResponse.json
        )

        val apiClient = FakeKTORClient(mockResponses)

        val repository = OctaneGGTeamRepository(apiClient)

        repository
            .getActiveRLCSTeams()
            .test {
                val teamList = awaitItem()

                with(teamList.first()) {
                    assertEquals("6332a14dda9d7ca1c7bb4688", this.id)
                    assertEquals("1s", this.name)
                    assertNull(this.imageUrl)
                    assertFalse(this.isFavorite)
                }

                awaitComplete()
            }
    }
}
