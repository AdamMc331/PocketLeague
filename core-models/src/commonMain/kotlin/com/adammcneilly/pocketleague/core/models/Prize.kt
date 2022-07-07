package com.adammcneilly.pocketleague.core.models

/**
 * Defines an [amount] of [currency] awarded to the winners of an event.
 *
 * Usually, this defines the total prize pool, which is split among top performers and not
 * a singular winner.
 */
data class Prize(
    val amount: Double = 0.0,
    val currency: String = "",
)
