package com.adammcneilly.pocketleague.core.datetime.test

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

/**
 * A custom implementation of a [Clock] that can be used to mock the current time.
 */
class FakeClock : Clock {

    var currentTime: Instant = Clock.System.now()

    override fun now(): Instant {
        return currentTime
    }
}
