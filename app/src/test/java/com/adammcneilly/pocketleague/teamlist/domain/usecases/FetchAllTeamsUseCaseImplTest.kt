package com.adammcneilly.pocketleague.teamlist.domain.usecases

import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.models.Team
import com.adammcneilly.pocketleague.teamlist.data.FakeTeamListService
import com.adammcneilly.pocketleague.teamlist.domain.models.FetchTeamListResult
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FetchAllTeamsUseCaseImplTest {
    private val fakeTeamListService = FakeTeamListService()
    private val useCase = FetchAllTeamsUseCaseImpl(
        service = fakeTeamListService,
    )

    @Test
    fun invokeSuccess() = runTest {
        val fakeTeam = Team(
            name = "Team Name",
            lightThemeLogoImageUrl = null,
            darkThemeLogoImageUrl = null,
            roster = emptyList(),
        )

        val mockServiceResult = Result.Success(listOf(fakeTeam))

        fakeTeamListService.mockAllTeamsResult(mockServiceResult)

        val actualResult = useCase.invoke()
        val expectedResult = FetchTeamListResult.Success(listOf(fakeTeam))
        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun invokeFailure() = runTest {
        val mockServiceResult = Result.Error(Throwable())

        fakeTeamListService.mockAllTeamsResult(mockServiceResult)

        val actualResult = useCase.invoke()
        val expectedResult = FetchTeamListResult.Failure
        assertThat(actualResult).isEqualTo(expectedResult)
    }
}
