package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.datetime.DebugTimeProvider
import com.adammcneilly.pocketleague.core.datetime.TimeProvider
import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import org.koin.dsl.module

/**
 * Defines any dependencies related to date and time management.
 */
val dateTimeModule = module {
    single<TimeProvider> {
        // SystemTimeProvider
        DebugTimeProvider()
    }

    single<DateTimeFormatter> {
        dateTimeFormatter()
    }
}
