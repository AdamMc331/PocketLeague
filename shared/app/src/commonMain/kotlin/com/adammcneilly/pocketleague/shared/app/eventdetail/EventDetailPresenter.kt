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
import kotlinx.coroutines.flow.combine
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

        var selectedStageIndex by remember {
            mutableStateOf(0)
        }

        var matchesForSelectedStage by remember {
            mutableStateOf(emptyList<MatchDetailDisplayModel>())
        }

        LaunchedEffect(Unit) {
            eventRepository
                .getEvent(eventId)
                .map(Event::toDetailDisplayModel)
                .onEach { eventDetail ->
                    println("ADAMLOG - updating displaymodel")
                    displayModel = eventDetail
                }
                .launchIn(this)

            val eventFlow = snapshotFlow {
                displayModel
            }

            val selectedStageIndexFlow = snapshotFlow {
                selectedStageIndex
            }

            eventFlow
                .combine(selectedStageIndexFlow) { event, stageIndex ->
                    val eventId = event.eventId
                    val stageId = event.stageSummaries[stageIndex].stageId
                    eventId to stageId
                }
                .flatMapLatest { (eventId, stageId) ->
                    matchRepository.getMatchesForEventStage(eventId, stageId)
                }
                .map { matchList ->
                    matchList.map(Match::toDetailDisplayModel)
                }
                .onEach { matchList ->
                    matchesForSelectedStage = matchList
                }
                .launchIn(this)
        }

        println("ADAMLOG - $displayModel, $selectedStageIndex, ${matchesForSelectedStage.size}")
        return EventDetailScreen.State(
            event = displayModel,
            selectedStageIndex = selectedStageIndex,
            matchesForSelectedStage = matchesForSelectedStage,
        ) { uiEvent ->
            when (uiEvent) {
                is EventDetailScreen.Event.StageSelected -> {
                    selectedStageIndex = uiEvent.stageIndex
                }
            }
        }
    }
}
