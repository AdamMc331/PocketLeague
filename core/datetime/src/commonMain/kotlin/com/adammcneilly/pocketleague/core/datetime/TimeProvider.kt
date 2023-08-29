package com.adammcneilly.pocketleague.core.datetime

/**
 * Similar to a kotlinx Clock instance, this time provider allows a platform
 * to specify [now], without any dependencies on a particular datetime library.
 */
interface TimeProvider {
    /**
     * Return a string representation (in ISO format) of the current time.
     */
    fun now(): String
}
