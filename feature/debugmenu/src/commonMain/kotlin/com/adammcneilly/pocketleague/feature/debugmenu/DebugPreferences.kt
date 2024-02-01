package com.adammcneilly.pocketleague.feature.debugmenu

/**
 * Defines the base preference information related to debug settings for the user.
 */
interface DebugPreferences {
    /**
     * Return true if we want to use the system time for API requests,
     * false if we want to use a debug time provider.
     */
    var useSystemTimeProvider: Boolean

    /**
     * Returns the ISO string for the date time to supply to a
     * debug time provider for API requests.
     *
     * NOTE: Only relevant in situations where [useSystemTimeProvider] is false.
     */
    var debugTimeProviderDate: String
}
