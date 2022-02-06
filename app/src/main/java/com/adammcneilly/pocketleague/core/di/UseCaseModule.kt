package com.adammcneilly.pocketleague.core.di

import com.adammcneilly.pocketleague.eventoverview.domain.usecases.FetchEventOverviewUseCase
import com.adammcneilly.pocketleague.eventoverview.domain.usecases.FetchEventOverviewUseCaseImpl
import com.adammcneilly.pocketleague.eventsummary.domain.usecases.FetchUpcomingEventsUseCase
import com.adammcneilly.pocketleague.eventsummary.domain.usecases.FetchUpcomingEventsUseCaseImpl
import com.adammcneilly.pocketleague.phase.domain.usecases.FetchPhaseDetailUseCase
import com.adammcneilly.pocketleague.phase.domain.usecases.FetchPhaseDetailUseCaseImpl
import com.adammcneilly.pocketleague.teamlist.domain.usecases.FetchAllTeamsUseCase
import com.adammcneilly.pocketleague.teamlist.domain.usecases.FetchAllTeamsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * A collection of all use case dependencies in the application.
 */
@Module
@InstallIn(SingletonComponent::class)
@Suppress("UndocumentedPublicFunction")
abstract class UseCaseModule {
    @Binds
    abstract fun bindFetchAllTeamsUseCase(
        fetchAllTeamsUseCase: FetchAllTeamsUseCaseImpl,
    ): FetchAllTeamsUseCase

    @Binds
    abstract fun bindFetchUpcomingEventsUseCase(
        fetchUpcomingEventsUseCase: FetchUpcomingEventsUseCaseImpl,
    ): FetchUpcomingEventsUseCase

    @Binds
    abstract fun bindFetchEventOverviewUseCase(
        fetchEventOverviewUseCase: FetchEventOverviewUseCaseImpl,
    ): FetchEventOverviewUseCase

    @Binds
    abstract fun bindFetchPhaseDetailUseCase(
        fetchPhaseDetailUseCase: FetchPhaseDetailUseCaseImpl,
    ): FetchPhaseDetailUseCase
}
