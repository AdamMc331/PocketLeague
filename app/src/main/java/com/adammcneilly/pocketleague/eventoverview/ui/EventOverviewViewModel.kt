package com.adammcneilly.pocketleague.eventoverview.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pocketleague.bracket.domain.models.BracketType
import com.adammcneilly.pocketleague.core.data.Result
import com.adammcneilly.pocketleague.core.ui.UIText
import com.adammcneilly.pocketleague.core.utils.DateTimeHelper
import com.adammcneilly.pocketleague.eventoverview.domain.models.EventOverview
import com.adammcneilly.pocketleague.eventoverview.domain.usecases.FetchEventOverviewUseCase
import com.adammcneilly.pocketleague.phase.domain.models.Phase
import com.adammcneilly.pocketleague.phase.ui.PhaseDisplayModel
import com.ramcosta.composedestinations.EventOverviewScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A state management container for the [EventOverviewScreen].
 */
@HiltViewModel
class EventOverviewViewModel @Inject constructor(
    private val fetchEventOverviewUseCase: FetchEventOverviewUseCase,
    private val dateTimeHelper: DateTimeHelper,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _viewState: MutableStateFlow<EventOverviewViewState> =
        MutableStateFlow(EventOverviewViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val navArgs = EventOverviewScreenDestination.argsFrom(savedStateHandle)

    init {
        viewModelScope.launch {
            val response = fetchEventOverviewUseCase(navArgs.eventId)

            _viewState.value = when (response) {
                is Result.Success -> {
                    EventOverviewViewState.Success(
                        event = response.data.toDisplayModel(dateTimeHelper),
                    )
                }
                is Result.Error -> {
                    EventOverviewViewState.Error(
                        errorMessage = UIText.StringText("Error fetching event overview."),
                    )
                }
            }
        }
    }
}

private fun EventOverview.toDisplayModel(
    dateTimeHelper: DateTimeHelper,
): EventOverviewDisplayModel {
    return EventOverviewDisplayModel(
        eventName = this.name,
        phases = this.phases.map { phase ->
            phase.toDisplayModel()
        },
        startDate = dateTimeHelper.getEventDayString(this.startDate),
    )
}

private fun Phase.toDisplayModel(): PhaseDisplayModel {
    return PhaseDisplayModel(
        phaseName = this.name,
        numPools = this.numPools.toString(),
        bracketType = when (this.bracketType) {
            BracketType.CUSTOM -> "Custom"
            BracketType.SINGLE_ELIMINATION -> "SE"
            BracketType.DOUBLE_ELIMINATION -> "DE"
            BracketType.UNKNOWN -> "Unknown"
        },
        numEntrants = this.numEntrants.toString(),
    )
}
