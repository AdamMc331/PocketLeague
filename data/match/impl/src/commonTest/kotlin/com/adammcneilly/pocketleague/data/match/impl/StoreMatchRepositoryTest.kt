package com.adammcneilly.pocketleague.data.match.impl

import app.cash.turbine.test
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.core.models.test.TestModel
import com.adammcneilly.pocketleague.core.models.test.matchBlueWinner
import com.adammcneilly.pocketleague.data.match.api.MatchListRequest
import com.adammcneilly.pocketleague.data.match.test.FakeLocalMatchService
import com.adammcneilly.pocketleague.data.match.test.FakeRemoteMatchService
import com.varabyte.truthish.assertThat
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class StoreMatchRepositoryTest {
    private val remoteMatchService = FakeRemoteMatchService()
    private val localMatchService = FakeLocalMatchService()

    private val repository = StoreMatchRepository(
        remoteMatchService = remoteMatchService,
        localMatchService = localMatchService,
    )

    @Test
    fun processSuccessRequest() = runTest {
        val request = MatchListRequest.Id(Match.Id("123"))
        val testMatch = TestModel.matchBlueWinner

        remoteMatchService.mockResponse(
            request = request,
            response = Result.success(listOf(testMatch)),
        )

        localMatchService.mockResponse(
            request = request,
            response = listOf(testMatch),
        )

        repository
            .stream(request)
            .test {
                assertThat(awaitItem()).isEqualTox(listOf(testMatch))
                assertThat(awaitItem()).isEqualTo(emptyList<Match>())
                assertThat(awaitItem()).isEqualTo(listOf(testMatch))
                val remainingEvents = cancelAndConsumeRemainingEvents()
                assertThat(remainingEvents).isEmpty()
            }
    }
}
