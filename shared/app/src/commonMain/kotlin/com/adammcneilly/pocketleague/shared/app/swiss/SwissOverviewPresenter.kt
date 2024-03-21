package com.adammcneilly.pocketleague.shared.app.swiss

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.adammcneilly.pocketleague.core.displaymodels.SwissTeamResultDisplayModel
import com.adammcneilly.pocketleague.core.displaymodels.toDisplayModel
import com.adammcneilly.pocketleague.core.models.SwissTeamResult
import com.slack.circuit.runtime.presenter.Presenter

class SwissOverviewPresenter(
    private val stageId: String,
    private val stageRepository: SwissStageRepository,
) : Presenter<SwissOverviewScreen.State> {
    @Composable
    override fun present(): SwissOverviewScreen.State {
        var teamResults by remember {
            mutableStateOf(emptyList<SwissTeamResultDisplayModel>())
        }

        LaunchedEffect(Unit) {
            val result = stageRepository.fetchSwissStageResults(
                stageId = stageId,
            )

            val resultList = result.getOrNull()

            if (resultList != null) {
                teamResults = resultList.map(SwissTeamResult::toDisplayModel)
            }
        }

        return SwissOverviewScreen.State(
            results = teamResults,
            eventSink = { event ->
                // Do something?
            },
        )
    }
}
