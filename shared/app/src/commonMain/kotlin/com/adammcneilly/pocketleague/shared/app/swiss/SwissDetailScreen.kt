package com.adammcneilly.pocketleague.shared.app.swiss

import com.adammcneilly.pocketleague.core.displaymodels.SwissTeamResultDisplayModel
import com.slack.circuit.runtime.CircuitUiState

class SwissDetailScreen {
    data class State(
        val teamResult: SwissTeamResultDisplayModel,
        val matches: List<SwissMatchDisplayModel>,
    ) : CircuitUiState
}
