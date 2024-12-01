package com.adammcneilly.pocketleague.shared.app.di

import com.adammcneilly.pocketleague.shared.app.domain.usecases.GetOngoingEventsUseCase
import com.adammcneilly.pocketleague.shared.app.domain.usecases.GetPastWeeksMatchesUseCase
import com.adammcneilly.pocketleague.shared.app.domain.usecases.GetUpcomingEventsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetPastWeeksMatchesUseCase(
            dateTimeFormatter = get(),
            matchRepository = get(),
            timeProvider = get(),
        )
    }

    single {
        GetOngoingEventsUseCase(
            dateTimeFormatter = get(),
            eventRepository = get(),
            localeHelper = get(),
            timeProvider = get(),
        )
    }

    single {
        GetUpcomingEventsUseCase(
            dateTimeFormatter = get(),
            eventRepository = get(),
            localeHelper = get(),
            timeProvider = get(),
        )
    }
}
