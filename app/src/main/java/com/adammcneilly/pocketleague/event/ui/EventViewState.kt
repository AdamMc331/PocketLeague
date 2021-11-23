package com.adammcneilly.pocketleague.event.ui

import com.adammcneilly.pocketleague.swiss.ui.SwissStageDisplayModel

data class EventViewState(
    val showLoading: Boolean = true,
    val showContent: Boolean = false,
    val showError: Boolean = false,
    val swissStage: SwissStageDisplayModel? = null,
)
