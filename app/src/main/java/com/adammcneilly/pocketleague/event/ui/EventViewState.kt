package com.adammcneilly.pocketleague.event.ui

import com.adammcneilly.pocketleague.swiss.ui.SwissStageDisplayModel

/**
 * All information necessary to render the UI for an RLCS event. This includes the
 * loading/content/error states.
 */
data class EventViewState(
    val showLoading: Boolean = true,
    val showContent: Boolean = false,
    val showError: Boolean = false,
    val swissStage: SwissStageDisplayModel? = null,
)
