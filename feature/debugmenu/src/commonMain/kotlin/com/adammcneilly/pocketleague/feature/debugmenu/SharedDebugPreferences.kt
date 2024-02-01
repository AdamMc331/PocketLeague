package com.adammcneilly.pocketleague.feature.debugmenu

import com.russhwolf.settings.Settings
import kotlinx.datetime.Clock

/**
 * Implementation of [DebugPreferences] that uses multiplatform [Settings].
 */
class SharedDebugPreferences(
    private val settings: Settings,
) : DebugPreferences {
    constructor() : this(settings = Settings())

    override var useSystemTimeProvider: Boolean
        get() = settings.getBoolean(KEY_USE_SYSTEM_TIME_PROVIDER, true)
        set(value) {
            settings.putBoolean(KEY_USE_SYSTEM_TIME_PROVIDER, value)
        }

    override var debugTimeProviderDate: String
        get() = settings.getString(KEY_DEBUG_TIME_PROVIDER_DATE, Clock.System.now().toString())
        set(value) {
            settings.putString(KEY_DEBUG_TIME_PROVIDER_DATE, value)
        }

    companion object {
        private const val KEY_USE_SYSTEM_TIME_PROVIDER = "use_system_time_provider"
        private const val KEY_DEBUG_TIME_PROVIDER_DATE = "debug_time_provider_date"
    }
}
