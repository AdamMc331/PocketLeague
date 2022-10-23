package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.test.testMatchDetailDisplayModelBlueWinner
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MatchDetailViewStateTest {

    @Test
    fun getTitleWhileLoading() {
        val state = MatchDetailViewState(
            matchDetailState = DataState.Loading,
        )

        assertNull(state.title)
    }

    @Test
    fun getTitleWhenError() {
        val state = MatchDetailViewState(
            matchDetailState = DataState.Error(Throwable()),
        )

        assertNull(state.title)
    }

    @Test
    fun getTitleWithSuccess() {
        val displayModel = testMatchDetailDisplayModelBlueWinner

        val state = MatchDetailViewState(
            matchDetailState = DataState.Success(displayModel),
        )

        val expectedTitle = "${displayModel.blueTeamResult.team.name} vs ${displayModel.orangeTeamResult.team.name}"
        assertEquals(expectedTitle, state.title)
    }
}
