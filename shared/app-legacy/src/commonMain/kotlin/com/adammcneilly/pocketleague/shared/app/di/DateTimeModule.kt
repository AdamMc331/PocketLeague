package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.datetime.DebugTimeProvider
import com.adammcneilly.pocketleague.core.datetime.SystemTimeProvider
import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import com.adammcneilly.pocketleague.feature.debugmenu.DebugPreferences
import org.koin.dsl.module

/**
 * Defines any dependencies related to date and time management.
 */
val dateTimeModule = module {
    factory<TimeProvider> {
        val preferences: DebugPreferences = get()

        if (preferences.useSystemTimeProvider) {
            SystemTimeProvider
        } else {
            DebugTimeProvider(
                dateString = preferences.debugTimeProviderDate,
            )
        }
    }

    single<DateTimeFormatter> {
        dateTimeFormatter()
    }
}
