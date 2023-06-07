package com.adammcneilly.pocketleague.core.models

/**
 * This represents a round within an [EventStage].
 *
 * @property[number] is the index of the round. For example, 1 being the first round. If this is used
 * to represent a double elimination bracket, rounds with negative numbers imply a lower bracket round.
 * @property[name] is a user friendly explanation of the round.
 */
data class StageRound(
    val number: Int,
    val name: String,
)
