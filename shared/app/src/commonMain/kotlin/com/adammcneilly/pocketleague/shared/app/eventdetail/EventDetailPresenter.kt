package com.adammcneilly.pocketleague.shared.app.eventdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.MatchDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.core.models.Match
import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * State management container for the event detail screen.
 */
class EventDetailPresenter(
    private val eventId: Event.Id,
    private val eventRepository: EventRepository,
    private val matchRepository: MatchRepository,
) : Presenter<EventDetailScreen.State> {

    @Composable
    override fun present(): EventDetailScreen.State {
        var displayModel by remember {
            mutableStateOf(EventDetailDisplayModel.placeholder)
        }

        var selectedStageId by remember {
            mutableStateOf(displayModel.getStageSummaries().first().stageId)
        }

        var matchesForSelectedStage by remember {
            mutableStateOf(
                listOf(
                    MatchDetailDisplayModel.placeholder,
                    MatchDetailDisplayModel.placeholder,
                    MatchDetailDisplayModel.placeholder,
                ),
            )
        }

        LaunchedEffect(Unit) {
            eventRepository
                .getEvent(eventId)
                .map(Event::toDetailDisplayModel)
                .onEach { eventDetail ->
                    displayModel = eventDetail
                }
                .launchIn(this)

            snapshotFlow {
                selectedStageId
            }.flatMapLatest { stageId ->
                matchRepository.getMatchesForEventStage(
                    eventId = displayModel.eventId,
                    stageId = stageId,
                )
            }.map { matches ->
                matches.map(Match::toDetailDisplayModel)
            }.onEach { matches ->
                matchesForSelectedStage = matches
            }.launchIn(this)
        }

        return EventDetailScreen.State(
            event = displayModel,
            selectedStageId = selectedStageId,
            matchesForSelectedStage = matchesForSelectedStage,
        ) { uiEvent ->
            when (uiEvent) {
                is EventDetailScreen.Event.StageSelected -> {
                    selectedStageId = uiEvent.stageId
                }
            }
        }
    }
}
