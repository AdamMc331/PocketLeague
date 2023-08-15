package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.core.datetime.DateTimeFormatter
import com.adammcneilly.pocketleague.core.datetime.DebugClock
import com.adammcneilly.pocketleague.core.datetime.dateTimeFormatter
import kotlinx.datetime.Clock
import org.koin.dsl.module

/**
 * Defines any dependencies related to date and time management.
 */
val dateTimeModule = module {
    single<Clock> {
//        Clock.System
        DebugClock()
    }

    single<DateTimeFormatter> {
        dateTimeFormatter()
    }
}
