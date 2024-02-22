package com.adammcneilly.pocketleague.shared.app.swiss

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.presenter.Presenter

class SwissOverviewPresenter(
    private val stageId: String,
    private val stageRepository: SwissStageRepository,
) : Presenter<SwissOverviewScreen.State> {
    @Composable
    override fun present(): SwissOverviewScreen.State {
        return SwissOverviewScreen.State(
            results = emptyList(),
            eventSink = { event ->
                // Do something?
            },
        )
    }
}
