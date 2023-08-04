package com.adammcneilly.pocketleague.shared.app.eventdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adammcneilly.pocketleague.core.displaymodels.EventDetailDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDetailDisplayModel
import com.adammcneilly.pocketleague.core.models.Event
import com.adammcneilly.pocketleague.data.event.EventRepository
import com.adammcneilly.pocketleague.data.match.MatchRepository
import com.slack.circuit.runtime.presenter.Presenter
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

        LaunchedEffect(Unit) {
            eventRepository
                .getEvent(eventId)
                .map(Event::toDetailDisplayModel)
                .onEach { eventDetail ->
                    displayModel = eventDetail
                }
                .launchIn(this)
        }

        return EventDetailScreen.State(
            event = displayModel,
            selectedStageIndex = selectedStageIndex,
        ) { uiEvent ->
            when (uiEvent) {
                is EventDetailScreen.Event.StageSelected -> {
                    selectedStageIndex = uiEvent.stageIndex
                }
            }
        }
    }
}
