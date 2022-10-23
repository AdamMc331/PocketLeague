package com.adammcneilly.pocketleague.shared.screens.matchdetail

import com.adammcneilly.pocketleague.core.data.DataState
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.test.blueWinner
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MatchDetailViewStateTest {

    @Test
    fun configureWhenLoading() {
        val state = MatchDetailViewState(
            matchDetailState = DataState.Loading,
        )

        assertNull(state.title)
        assertEquals(MatchDetailDisplayModel.placeholder, state.matchDetail)
    }

    @Test
    fun configureWhenError() {
        val state = MatchDetailViewState(
            matchDetailState = DataState.Error(Throwable()),
        )

        assertNull(state.title)
        assertNull(state.matchDetail)
    }

    @Test
    fun configureWhenSuccess() {
        val displayModel = MatchDetailDisplayModel.blueWinner

        val state = MatchDetailViewState(
            matchDetailState = DataState.Success(displayModel),
        )

        val expectedTitle = "${displayModel.blueTeamResult.team.name} vs ${displayModel.orangeTeamResult.team.name}"

        assertEquals(expectedTitle, state.title)
        assertEquals(displayModel, state.matchDetail)
    }
}
