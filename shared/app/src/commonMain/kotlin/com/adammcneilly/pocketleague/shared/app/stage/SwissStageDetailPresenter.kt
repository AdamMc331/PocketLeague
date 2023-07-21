package com.adammcneilly.pocketleague.shared.app.stage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adammcneilly.pocketleague.core.displaymodels.SwissStageTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDisplayModel
import com.adammcneilly.pocketleague.core.models.SwissStageTeamResult
import com.adammcneilly.pocketleague.data.event.EventRepository
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SwissStageDetailPresenter(
    private val eventId: String,
    private val stageId: String,
    private val eventRepository: EventRepository,
) : Presenter<SwissStageDetailScreen.State> {

    @Composable
    override fun present(): SwissStageDetailScreen.State {
        var teamResults by remember {
            mutableStateOf(emptyList<SwissStageTeamResultDisplayModel>())
        }

        LaunchedEffect(Unit) {
            eventRepository
                .getSwissStageResults(eventId, stageId)
                .onEach { domainResults ->
                    teamResults = domainResults.map(SwissStageTeamResult::toDisplayModel)
                }
                .launchIn(this)
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
