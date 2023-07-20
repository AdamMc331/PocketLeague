package com.adammcneilly.pocketleague.shared.app.stage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adammcneilly.pocketleague.core.displaymodels.SwissStageTeamResultDisplayModel
import com.slack.circuit.runtime.presenter.Presenter

class SwissStageDetailPresenter(
    private val eventId: String,
    private val stageId: String,
) : Presenter<SwissStageDetailScreen.State> {

    @Composable
    override fun present(): SwissStageDetailScreen.State {
        var teamResults by remember {
            mutableStateOf(emptyList<SwissStageTeamResultDisplayModel>())
        }

        LaunchedEffect(Unit) {
            // TODO: Load teamResults
        }

        return SwissStageDetailScreen.State(
            teamResults = teamResults,
        ) { event ->
            when (event) {
                is SwissStageDetailScreen.Event.TeamResultClicked -> {
                    // TODO: Handle click
                }
            }
        }
    }
}
