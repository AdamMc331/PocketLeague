package com.adammcneilly.pocketleague.bracket.ui

/**
 * A collection of [rounds] for the bracket phase of an event.
 */
data class BracketPhaseDisplayModel(
    val rounds: List<BracketRoundDisplayModel>,
)
