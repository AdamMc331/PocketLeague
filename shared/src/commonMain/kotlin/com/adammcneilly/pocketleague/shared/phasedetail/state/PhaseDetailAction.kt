package com.adammcneilly.pocketleague.shared.phasedetail.state

/**
 * A collection of actions that can occur on the phase detail screen.
 */
sealed class PhaseDetailAction {
    /**
     * Triggered when we want to fetch detailed information about a phase.
     */
    data class FetchPhaseDetail(
        val phaseId: String,
    ) : PhaseDetailAction()
}
