package com.adammcneilly.pocketleague.core.datetime

import kotlinx.datetime.Clock

/**
 * Provide the default [Clock] instance to use everywhere in the app.
 *
 * Utility function makes this easy to swap in a [DebugClock] when we want
 * to test.
 */
fun defaultClock(): Clock = Clock.System // DebugClock()
