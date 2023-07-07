package com.adammcneilly.pocketleague.shared.app.match

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.data.match.OctaneGGMatchService
import com.slack.circuit.runtime.presenter.Presenter

/**
 * State management container for the [MatchDetailScreen].
 */
class MatchDetailPresenter(
    private val matchId: String,
) : Presenter<MatchDetailScreen.State> {

    @Composable
    override fun present(): MatchDetailScreen.State {
        var match by remember {
            mutableStateOf(MatchDetailDisplayModel.placeholder)
        }

        LaunchedEffect(Unit) {
            match = OctaneGGMatchService()
                .getMatchDetail(matchId)
                .getOrNull()
                ?.toDetailDisplayModel()
                ?: MatchDetailDisplayModel.placeholder
        }

        return MatchDetailScreen.State(
            displayModel = match,
        ) { event ->
            when (event) {
                // No events yet
                else -> {}
            }
        }
    }
}
