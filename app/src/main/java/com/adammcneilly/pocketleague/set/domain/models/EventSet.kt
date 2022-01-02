package com.adammcneilly.pocketleague.set.domain.models

/**
 * A [EventSet] represents a series or collection of series between two teams in an event.
 */
data class EventSet(
    val fullRoundText: String,
    val displayScore: String,
    val round: String,
    val winnerId: String,
    val slots: List<SetSlot>,
)
