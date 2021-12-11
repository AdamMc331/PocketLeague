package com.adammcneilly.pocketleague.swiss.domain.models

/**
 * This data class represents one specific stage of the swiss portion of an event. The [rounds]
 * can either be a single round (in case of round 1), or potentially have high/mid/low rounds, like
 * round 3. This will vary based on the number of teams participating.
 */
data class SwissStage(
    val rounds: List<SwissRound>,
)
